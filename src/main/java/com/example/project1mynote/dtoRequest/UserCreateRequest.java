package com.example.project1mynote.dtoRequest;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserCreateRequest {
    private String userName;
    private String email;
    private String passWord;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
