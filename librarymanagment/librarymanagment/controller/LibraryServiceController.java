package com.capgemini.app.ofm.presentation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.app.ofm.core.facadews.LibraryBookService;
import com.capgemini.app.ofm.core.persistence.entity.Book;
import com.capgemini.app.ofm.core.persistence.entity.Category;

@RestController
public class LibraryServiceController {

	@Autowired
	LibraryBookService libraryBookService;

	@GetMapping(value = "/findById/{bookId}")
	public Optional<Book> findById(@PathVariable("bookId") int bookId) {
		final Optional<Book> book = libraryBookService.findById(bookId);
		return book;
	}

	@GetMapping(value = "/findAllBooks")
	public List<Book> findAllBooks() {
		final List<Book> book = libraryBookService.findAllBooks();
		return book;
	}

	@PostMapping("/createBook")
	public String createBook(@RequestParam("bookAdditionMode") String bookAdditionMode, @RequestParam("bookNumber") int bookNumber, @RequestParam("bookTitle") String bookTitle,
			@RequestParam("authorName") String authorName, @RequestParam("TagsName") String TagsName, @RequestParam("customTagsName") String customTagsName,
			@RequestParam("numberOfCopies") int numberOfCopies, @RequestParam("categoryName") String categoryName) {
		final String msg = libraryBookService.createBook(bookAdditionMode, bookNumber, bookTitle, authorName, TagsName, customTagsName, numberOfCopies, categoryName);
		return msg;

	}

	@PutMapping("/updateBook/{bookId}")
	public String updateBook(@PathVariable("bookId") int bookId, @RequestParam("bookAdditionMode") String bookAdditionMode, @RequestParam("bookNumber") int bookNumber,
			@RequestParam("bookTitle") String bookTitle, @RequestParam("authorName") String authorName, @RequestParam("TagsName") String TagsName,
			@RequestParam("customTagsName") String customTagsName, @RequestParam("numberOfCopies") int numberOfCopies, @RequestParam("categoryName") String categoryName) {
		final String msg = libraryBookService.updateBook(bookId, bookAdditionMode, bookNumber, bookTitle, authorName, TagsName, customTagsName, numberOfCopies, categoryName);
		return msg;

	}

	@GetMapping(value = "/findBookByAuthorName")
	public List<Book> findByAuthorName(@RequestParam("authorName") String authorName) {
		final List<Book> booklist = libraryBookService.findByAuthorName(authorName);
		return booklist;
	}

	@GetMapping(value = "/deleteBook/{bookId}")
	public String deleteBook(@PathVariable("bookId") int bookId) {
		final String book = libraryBookService.deleteBook(bookId);
		return book;
	}

	@GetMapping(value = "/findCategoryById/{categoryId}")
	public Optional<Category> findCategoryById(@PathVariable("CategoryId") int categoryId) {
		final Optional<Category> cat = libraryBookService.findCategoryById(categoryId);
		return cat;
	}

	@GetMapping(value = "/findAllCategory")
	public List<Category> findAllCategory() {
		final List<Category> cat = libraryBookService.findAllCategory();
		return cat;

	}

	@GetMapping(value = "/deleteCategory/{categoryId}")
	public String deleteCategory(@PathVariable("categoryId") int categoryId) {
		final String book = libraryBookService.deleteCategory(categoryId);
		return book;
	}

}
