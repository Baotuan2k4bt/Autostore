package com.example.autostore.mapper;

import com.example.autostore.dto.user.request.UserCreationRequest;
import com.example.autostore.dto.user.request.UserUpdateRequest;
import com.example.autostore.dto.user.response.UserResponse;
import com.example.autostore.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    AppUser toUser(UserCreationRequest request);

    UserResponse toUserResponse(AppUser user);

    void updateUser(@MappingTarget AppUser user, UserUpdateRequest request);
}
