package com.example.project1mynote.dtoRequest;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateRequest {
    private String passWord;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
