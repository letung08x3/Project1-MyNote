package com.example.project1mynote.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
public class UserDto {
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public UserDto(String userName, String firstName, String lastName, LocalDate dob, String email) {
        this.email = email;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }


    public UserDto() {

    }
}
