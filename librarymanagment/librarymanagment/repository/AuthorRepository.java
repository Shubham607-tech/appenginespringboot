package com.capgemini.app.ofm.core.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.app.ofm.core.persistence.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

	public Author findByName(String authorName);
}
