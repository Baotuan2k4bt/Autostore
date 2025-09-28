package com.example.autostore.service;

import com.example.autostore.dto.user.request.UserCreationRequest;
import com.example.autostore.dto.user.request.UserUpdateRequest;
import com.example.autostore.dto.user.response.UserResponse;
import com.example.autostore.exception.AppException;
import com.example.autostore.exception.ErrorCode;
import com.example.autostore.mapper.UserMapper;
import com.example.autostore.model.AppUser;
import com.example.autostore.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        AppUser user = userMapper.toUser(request);
        user.setUserPassword(passwordEncoder.encode(request.getUserPassword()));
        user.setRole(AppUser.ROLE_USER);
        var saveUser = userRepository.save(user);
        return userMapper.toUserResponse(saveUser);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserResponse> getAllUsers(){
        List<AppUser> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id == principal.id")
    public UserResponse getUser(Integer  id) {
      return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
    }

    @PreAuthorize("hasAuthority('ADMIN') or #id == principal.id")
    public UserResponse updateUser(Integer  id, UserUpdateRequest request){
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        userMapper.updateUser(user, request);
        AppUser updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(Integer  id) {
       userRepository.deleteById(id);
    }
}
