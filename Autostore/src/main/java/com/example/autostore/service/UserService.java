package com.example.autostore.service;

import com.example.autostore.dto.request.UserCreationRequest;
import com.example.autostore.dto.request.UserUpdateRequest;
import com.example.autostore.dto.response.UserResponse;
import com.example.autostore.exception.AppException;
import com.example.autostore.exception.ErrorCode;
import com.example.autostore.mapper.UserMapper;
import com.example.autostore.model.entity.User;
import com.example.autostore.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }

        userMapper.toUser(request);

        var saveUser = userRepository.save(userMapper.toUser(request));
        return userMapper.toUserResponse(saveUser);
    }

    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUser(String id) {
      return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        userMapper.updateUser(user, request);
        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    public void deleteUser(String id) {
       userRepository.deleteById(id);
    }
}
