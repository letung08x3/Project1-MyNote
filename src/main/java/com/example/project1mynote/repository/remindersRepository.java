package com.example.project1mynote.repository;

import com.example.project1mynote.entity.Reminders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface remindersRepository extends JpaRepository<Reminders, Integer> {
}
