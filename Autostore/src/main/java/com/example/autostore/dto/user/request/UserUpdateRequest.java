package com.example.autostore.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    @Size(min = 8, message = "INVALID_PASSWORD")
    String userPassword;

    @Email(message = "EMAIL_INVALID")
    String userEmail;

    String userPhone;

    String userFullName;

    Boolean userIsActive;

}
