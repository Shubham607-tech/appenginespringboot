package com.capgemini.app.ofm.core.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CATEGORY_TEST")
@Data
public class Category {
	private int categoryId;
	private String categoryName;

	@OneToMany(mappedBy = "category")
	private List<Book> book;


}
