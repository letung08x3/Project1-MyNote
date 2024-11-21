package com.example.project1mynote.repository;

import com.example.project1mynote.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface noteRepository extends JpaRepository<Notes, Integer> {
}
