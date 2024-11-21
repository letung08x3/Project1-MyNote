package com.example.project1mynote.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String email;
    private String password;
    private Boolean rememberMe;

}
