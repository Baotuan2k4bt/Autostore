package com.example.autostore.mapper;

import com.example.autostore.dto.request.UserCreationRequest;
import com.example.autostore.dto.request.UserUpdateRequest;
import com.example.autostore.dto.response.UserResponse;
import com.example.autostore.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
