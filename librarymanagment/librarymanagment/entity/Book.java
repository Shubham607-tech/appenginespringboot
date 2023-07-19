package com.capgemini.app.ofm.core.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "BOOK_TEST")

@Data
public class Book {

	@Id
	@Column(name = "bookId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;

	@Column(name = "bookAdditionMode")
	private String bookAdditionMode;

	@Column(name = "bookNumber")
	private int bookNumber;

	@Column(name = "bookTitle")
	private String bookTitle;

	@Column(name = "numberOfCopies")
	private int numberOfCopies;

	@ManyToOne
	@JoinColumn(name = "authorName")
	private Author author;

	@ManyToOne
	@JoinColumn(name = "tagsName")
	@JoinColumn(name = "customTagsName")
	private Tags tags;

	@ManyToOne
	@JoinColumn(name = "categoryName")
	private Category category;








}
