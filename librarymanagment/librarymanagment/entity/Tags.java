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
@Table(name = "TAGS_TEST")
@Data
public class Tags {

	@Id
	@Column(name = "tagsId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int tagsId;

	@Column(name = "tagsName")
	private String tagsName;

	@Column(name = "customTagsName")
	private String customTagsName;

	@OneToMany(mappedBy = "tags")
	private List<Book> book;




}
