package com.zigato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.IngredientItem;

public interface IngredientItemRepo extends JpaRepository<IngredientItem, Long> {
	public List<IngredientItem> findByRestaurentId(long id);
}