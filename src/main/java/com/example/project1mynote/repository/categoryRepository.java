package com.example.project1mynote.repository;

import com.example.project1mynote.entity.Category;
import com.example.project1mynote.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(CategoryName categoryName);  // Tìm Category theo categoryName

}
