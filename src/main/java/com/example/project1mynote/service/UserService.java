package com.example.project1mynote.service;


import com.example.project1mynote.dto.UserDto;
import com.example.project1mynote.dtoRequest.UserCreateRequest;
import com.example.project1mynote.dtoRequest.UserUpdateRequest;
import com.example.project1mynote.entity.User;
import com.example.project1mynote.repository.userRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements InUserService, UserDetailsService {
    @Autowired
    userRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserDto> getAllUser(){
        List<User> userList = userRepository.findAll();

        // Tạo danh sách UserDto từ danh sách User

        return userList.stream()
                .map(user -> new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getDob(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserCreateRequest userCreateRequest) {
        User newUser = new User();
        newUser.setUsername(userCreateRequest.getUserName());
        newUser.setEmail(userCreateRequest.getEmail());
        newUser.setFirstName(userCreateRequest.getFirstName());
        newUser.setLastName(userCreateRequest.getLastName());
        newUser.setDob(userCreateRequest.getDob());

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(userCreateRequest.getPassWord());
        newUser.setPassWord(encodedPassword);
        userRepository.save(newUser);

        UserDto userDto = new UserDto();
        userDto.setUserName(newUser.getUsername());
        userDto.setEmail(newUser.getEmail());
        userDto.setDob(newUser.getDob());
        userDto.setFirstName(newUser.getFirstName());
        userDto.setLastName(newUser.getLastName());
        return userDto;
    }

    @Override
    public UserDto getUserById(int id) {
        return null;
    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public UserDto updateUser(UserUpdateRequest userUpdateRequest) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // tìm thông tin người dùng từ db
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Trả về đối tượng UserDetails dựa trên thông tin người dùng tìm thấy
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassWord(),
                new ArrayList<>()
        );


    }
}
