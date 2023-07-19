package com.capgemini.app.ofm.core.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.app.ofm.core.persistence.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findByName(String categoryName);

}
