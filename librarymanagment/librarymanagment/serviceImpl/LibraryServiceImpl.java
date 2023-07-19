package com.capgemini.app.ofm.core.facade.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.app.ofm.core.facadews.LibraryBookService;
import com.capgemini.app.ofm.core.persistence.entity.Author;
import com.capgemini.app.ofm.core.persistence.entity.Book;
import com.capgemini.app.ofm.core.persistence.entity.Category;
import com.capgemini.app.ofm.core.persistence.entity.Tags;
import com.capgemini.app.ofm.core.persistence.repository.AuthorRepository;
import com.capgemini.app.ofm.core.persistence.repository.BookRepository;
import com.capgemini.app.ofm.core.persistence.repository.CategoryRepository;
import com.capgemini.app.ofm.core.persistence.repository.TagsRepository;

@Service
public class LibraryServiceImpl implements LibraryBookService {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	TagsRepository tagsRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Optional<Book> findById(int bookId) {
		return bookRepository.findById(bookId);
	}

	@Override
	public List<Book> findAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public String createBook(String bookAdditionMode, int bookNumber, String bookTitle, String authorName, String tagsName, String customTagsName, int numberOfCopies, String categoryName) {

		Book book = new Book();
		Author author = new Author();
		Tags tags = new Tags();
		Category category = new Category();
		book.setBookAdditionMode(bookAdditionMode);
		book.setBookNumber(bookNumber);
		book.setBookTitle(bookTitle);
		author.setAuthorName(authorName);
		tags.setTagsName(tagsName);
		tags.setCustomTagsName(customTagsName);
		book.setNumberOfCopies(numberOfCopies);
		category.setCategoryName(categoryName);
		bookRepository.save(book);
		authorRepository.save(author);
		tagsRepository.save(tags);
		categoryRepository.save(category);
		return "Book created successfully ";

	}

	@Override
	public String updateBook(int bookId, String bookAdditionMode, int bookNumber, String bookTitle, String authorName, String tagsName, String customTagsName, int numberOfCopies,
			String categoryName) {
		Book updte = bookRepository.findById(bookId).get();
		updte.setBookAdditionMode(bookAdditionMode);
		updte.setBookNumber(bookNumber);
		updte.setBookTitle(bookTitle);
		updte.setNumberOfCopies(numberOfCopies);

		Author a = authorRepository.findByName(authorName);
		List<Book> lb = a.getBook();
		lb.add(updte);
		a.setBook(lb);
		updte.setAuthor(a);
		authorRepository.save(a);
		bookRepository.save(updte);

		Tags t = tagsRepository.findByName(tagsName);
		List<Book> lbt = t.getBook();
		lbt.add(updte);
		t.setBook(lbt);
		updte.setTags(t);
		tagsRepository.save(t);

		Tags tc = tagsRepository.findByName(customTagsName);
		List<Book> lbtc = tc.getBook();
		lbtc.add(updte);
		tc.setBook(lbtc);
		updte.setTags(t);
		tagsRepository.save(t);

		Category cn = categoryRepository.findByName(categoryName);
		List<Book> lbcn = cn.getBook();
		lbcn.add(updte);
		cn.setBook(lbcn);
		updte.setCategory(cn);
		categoryRepository.save(cn);

		return "Book updated successfully ";

	}

	@Override
	public List<Book> findByAuthorName(String authorName) {
		Author an = authorRepository.findByName(authorName);
		return an.getBook();

	}

	public List<Book> findBooks() {

		return bookRepository.findByCopies();

	}

	@Override
	public String deleteBook(int bookId) {
		bookRepository.deleteById(bookId);
		return "Book deleted successfully";

	}

	@Override
	public Optional<Category> findCategoryById(int categoryId) {
		return categoryRepository.findById(categoryId);
	}

	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public String deleteCategory(int categoryId) {
		categoryRepository.deleteById(categoryId);
		return "Category deleted successfully";
	}

}
