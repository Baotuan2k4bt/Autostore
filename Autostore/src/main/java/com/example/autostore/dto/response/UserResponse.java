package com.example.autostore.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    String id;
    String username;
    String firstName;
    String lastName;
    String email;
    LocalDate dob;
    // Set<RoleResponse> roles; // Uncomment if you want to include user roles
}
