package com.example.autostore.service;

import com.example.autostore.dto.user.request.AuthenticationRequest;
import com.example.autostore.dto.user.request.IntrospectRequest;
import com.example.autostore.dto.user.request.LogoutRequest;
import com.example.autostore.dto.user.request.RefreshRequest;
import com.example.autostore.dto.user.response.AuthenticationResponse;
import com.example.autostore.dto.user.response.IntrospectResponse;
import com.example.autostore.exception.AppException;
import com.example.autostore.exception.ErrorCode;
import com.example.autostore.model.AppUser;
import com.example.autostore.model.InvalidatedToken;
import com.example.autostore.repository.InvalidatedTokenRepository;
import com.example.autostore.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class AuthenticationService {
    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;

    @NonFinal
    @Value("${jwt.signerkey}")
    String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    long VALID_DURATION;  // access token TTL

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    long REFRESHABLE_DURATION;  // refresh token TTL

    // ============= LOGIN =============
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (!passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // Tạo cả access và refresh token
        String accessToken = generateToken(user, VALID_DURATION);
        String refreshToken = generateToken(user, REFRESHABLE_DURATION);

        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // ============= REFRESH TOKEN =============
    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        var username = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByUserName(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        // Tạo access token mới, giữ nguyên refresh token
        String newAccessToken = generateToken(user, VALID_DURATION);

        return AuthenticationResponse.builder()
                .token(newAccessToken)
                .refreshToken(request.getToken())
                .authenticated(true)
                .build();
    }

    // ============= LOGOUT =============
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = verifyToken(request.getToken(), true);
            String jti = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            invalidatedTokenRepository.save(
                    InvalidatedToken.builder().id(jti).expiryTime(expiryTime).build()
            );
        } catch (AppException e) {
            log.info("Token already expired");
        }
    }

    // ============= INTROSPECT =============
    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        boolean isValid = true;
        try {
            verifyToken(request.getToken(), false);
        } catch (AppException e) {
            isValid = false;
        }
        return IntrospectResponse.builder().valid(isValid).build();
    }

    // ============= UTILS =============

    private String generateToken(AppUser user, long durationSeconds) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(user.getUserName())
                .issuer("autostore")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(durationSeconds, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", user.getRole())
                .build();

        try {
            JWSObject jws = new JWSObject(header, new Payload(claims.toJSONObject()));
            jws.sign(new MACSigner(SIGNER_KEY.getBytes(StandardCharsets.UTF_8)));
            return jws.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException("Cannot generate token", e);
        }
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes(StandardCharsets.UTF_8));

        boolean verified = signedJWT.verify(verifier);
        if (!verified) throw new AppException(ErrorCode.UNAUTHENTICATED);

        Date now = new Date();
        Date expiryTime = (isRefresh)
                ? new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant()
                .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        if (expiryTime == null || !expiryTime.after(now)) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }
}
