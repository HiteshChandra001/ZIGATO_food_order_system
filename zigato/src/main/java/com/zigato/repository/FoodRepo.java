package com.zigato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zigato.model.Food;

public interface FoodRepo extends JpaRepository<Food, Long> {
	List<Food> findByRestaurentId(long restId);

	@Query("SELECT f FROM Food WHERE lower(f.name) LIKE lower(concat('%',:keyword,'%')) OR lower(f.category.name) LIKE lower(concat('%',:keyword,'%'))")
	List<Food> searchFood(@Param("keyword") String keyword);
}
