package com.capgemini.app.ofm.core.facadews;

import java.util.List;
import java.util.Optional;

import com.capgemini.app.ofm.core.persistence.entity.Book;
import com.capgemini.app.ofm.core.persistence.entity.Category;

public interface LibraryBookService {

	public String createBook(String bookAdditionMode, int bookNumber, String bookTitle, String authorName, String tagsName, String customTagsName, int numberOfCopies, String categoryName);

	public String updateBook(int bookId, String bookAdditionMode, int bookNumber, String bookTitle, String authorName, String tagsName, String customTagsName, int numberOfCopies, String categoryName);

	public List<Book> findAllBooks();

	public String deleteBook(int bookId);

	public List<Book> findByAuthorName(String authorName);

	Optional<Book> findById(int bookId);

	public Optional<Category> findCategoryById(int categoryId);

	public List<Category> findAllCategory();

	public String deleteCategory(int categoryId);

}
