package com.husnasari.emporium_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.husnasari.emporium_backend.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryId(String categoryId);
}
