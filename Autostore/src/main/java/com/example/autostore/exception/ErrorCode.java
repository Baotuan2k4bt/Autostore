package com.example.autostore.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "An uncategorized error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid key provided", HttpStatus.BAD_REQUEST),
    USER_EXISTS(1002, "User already exists", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} character", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} character ", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTS(1005, "User does not exist", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "User is not authenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "User is not authorized to perform this action", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Invalid date of birth must be at least {min} years old", HttpStatus.BAD_REQUEST)

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}
