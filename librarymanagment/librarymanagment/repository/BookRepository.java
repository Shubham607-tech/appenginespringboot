package com.capgemini.app.ofm.core.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.app.ofm.core.persistence.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	public Book findByName(String authorName);

	@Query("SELECT b from Book b WHERE b.numberOfCopies >= 5")
	List<Book> findByCopies();

}
