package com.zigato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.IngredientCategory;

public interface IngredientCategoryRepo extends JpaRepository<IngredientCategory, Long> {
	public List<IngredientCategory> findByRestaurentId(long id);
}
