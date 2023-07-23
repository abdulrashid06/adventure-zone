package com.adventure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adventure.model.Category;


public interface CategoryRespository extends JpaRepository<Category, Integer> {

}
