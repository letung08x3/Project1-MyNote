package com.example.project1mynote.controller;

import com.example.project1mynote.dto.UserDto;
import com.example.project1mynote.dtoRequest.UserCreateRequest;
import com.example.project1mynote.service.InUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private InUserService inUserService;

    @GetMapping
    public List<UserDto> getAllUser(){
        return inUserService.getAllUser();
    }

    @PostMapping("/create")
    public UserDto createUser (@RequestBody UserCreateRequest userCreateRequest){
        return inUserService.createUser(userCreateRequest);
    }
}
