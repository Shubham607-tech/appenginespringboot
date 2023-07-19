package com.capgemini.app.ofm.core.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "AUTHOR_TEST")
@Data
public class Author {

	@Id
	@Column(name = "authorId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int authorId;

	@Column(name = "authorName")
	private String authorName;

	@OneToMany(mappedBy = "author")
	private List<Book> book;



}
