package com.example.project1mynote.repository;

import com.example.project1mynote.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface userRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);
}
