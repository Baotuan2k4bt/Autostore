package com.example.autostore.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @NotBlank(message = "USERNAME_REQUIRED")
    @Size(min = 3, max = 20, message = "USERNAME_INVALID")
    String userName;

    @NotBlank(message = "PASSWORD_REQUIRED")
    @Size(min = 8, message = "INVALID_PASSWORD")
    String userPassword;

    @Email(message = "EMAIL_INVALID")
    String userEmail;

    String userPhone;
    Boolean userIsActive = true;

    String userFullName;

}
