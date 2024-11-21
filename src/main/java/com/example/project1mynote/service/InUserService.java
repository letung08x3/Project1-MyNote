package com.example.project1mynote.service;

import com.example.project1mynote.dto.UserDto;
import com.example.project1mynote.dtoRequest.UserCreateRequest;
import com.example.project1mynote.dtoRequest.UserUpdateRequest;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InUserService {
    List<UserDto> getAllUser();

    UserDto createUser(UserCreateRequest userCreateRequest);
    UserDto getUserById(int id);
    void deleteUserById(int id);
    UserDto updateUser(UserUpdateRequest userUpdateRequest);

}


