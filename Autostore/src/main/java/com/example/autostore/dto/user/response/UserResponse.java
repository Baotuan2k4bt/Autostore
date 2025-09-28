package com.example.autostore.dto.user.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Integer userId;
    String userName;
    String userEmail;
    String userPhone;
    String userFullName;
    Boolean userIsActive;
    String role;

    // Nếu sau này cần thì thêm:
    // Set<RoleResponse> roles;
}
