package com.capgemini.app.ofm.core.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.app.ofm.core.persistence.entity.Tags;

public interface TagsRepository extends JpaRepository<Tags, Integer> {

	public Tags findByName(String tagsName);

}
