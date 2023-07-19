package com.capgemini.app.ofm.core.facade.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.app.ofm.core.facade.TestConfigLibraryService;
import com.capgemini.app.ofm.core.persistence.entity.Author;
import com.capgemini.app.ofm.core.persistence.entity.Book;
import com.capgemini.app.ofm.core.persistence.entity.Category;
import com.capgemini.app.ofm.core.persistence.repository.AuthorRepository;
import com.capgemini.app.ofm.core.persistence.repository.BookRepository;
import com.capgemini.app.ofm.core.persistence.repository.CategoryRepository;
import com.capgemini.app.ofm.core.persistence.repository.TagsRepository;

@SpringBootTest(classes = { TestConfigLibraryService.class })
public class LibraryServiceImplTest {

	@Autowired
	LibraryServiceImpl libraryServiceImpl;

	@MockBean
	BookRepository bookRepository;

	@MockBean
	AuthorRepository authorRepository;

	@MockBean
	TagsRepository tagsRepository;

	@MockBean
	CategoryRepository categoryRepository;

	@Test
	public void createBook() {

		String result = libraryServiceImpl.createBook("General", 123, "Java", "James Goasling", "Reference", "Details of Java", 10, "Technicle");
		assertEquals("Book created successfully ", result);
		assertNotNull(result);
	}

	@Test
	public void updateBook() {
		String result = libraryServiceImpl.updateBook(1, "General", 123, "Java", "James Goasling", "Reference", "Details of Java", 10, "Technicle");
		assertEquals("Book updated successfully ", result);
		assertNotNull(result);

	}

	@Test
	public void findById() {
		Book book = new Book();
		when(bookRepository.findById(1)).thenReturn(Optional.of(book));
		Optional<Book> result = libraryServiceImpl.findById(1);
		assertEquals(book, result);
		assertNotNull(result);

	}

	@Test
	public void findAllBooks() {
		List<Book> booklist = new ArrayList<>();
		when(bookRepository.findAll()).thenReturn(booklist);
		List<Book> result = libraryServiceImpl.findAllBooks();
		assertEquals(booklist, result);
		assertNotNull(result);
	}

	@Test
	public void findBookByAuthorName() {
		List<Book> booklist = new ArrayList<>();
		when(authorRepository.findByName("James Goasling")).thenReturn((Author) booklist);
		List<Book> result = libraryServiceImpl.findByAuthorName("James Goasling");
		assertEquals(booklist, result);
		assertNotNull(result);
	}

	@Test
	public void deleteBook() {
		String result = libraryServiceImpl.deleteBook(1);
		assertEquals("Book deleted successfully ", result);
		assertNotNull(result);
	}

	@Test
	public void findCategoryById() {

		Category category = new Category();
		when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
		Optional<Category> result = libraryServiceImpl.findCategoryById(1);
		assertEquals(category, result);
		assertNotNull(result);
	}

	@Test
	public void findAllCategory() {
		List<Category> category = new ArrayList<>();
		when(categoryRepository.findAll()).thenReturn(category);
		List<Category> result = libraryServiceImpl.findAllCategory();
		assertEquals(category, result);
		assertNotNull(result);
	}

	@Test
	public void deleteCategory() {
		String result = libraryServiceImpl.deleteCategory(1);
		assertEquals("Category deleted successfully", result);
		assertNotNull(result);
	}

}
