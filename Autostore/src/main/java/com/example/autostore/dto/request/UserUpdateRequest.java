package com.example.autostore.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {


    @Size(min = 8, message = "INVALID_PASSWORD")
    String password;
    String firstName;
    String lastName;

    @Email
    String email;
    LocalDate dob;
}
