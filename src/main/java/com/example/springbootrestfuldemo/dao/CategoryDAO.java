package com.example.springbootrestfuldemo.dao;

import com.example.springbootrestfuldemo.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {

}
