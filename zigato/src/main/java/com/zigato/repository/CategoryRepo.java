package com.zigato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
	public List<Category> findByRestaurentId(long id);
}
