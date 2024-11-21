package com.example.project1mynote.repository;

import com.example.project1mynote.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface noteRepository extends JpaRepository<Notes, Integer> {
    List<Notes> findByUser_username(String username);
//    Optional<Notes> findByIdAndUserUsername(Long id, String username);

}
