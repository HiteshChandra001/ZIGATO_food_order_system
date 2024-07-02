package com.zigato.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zigato.model.Restaurent;

public interface RestaurentRepo extends JpaRepository<Restaurent, Long> {
	
	
	Optional<Restaurent> findByOwnerId(long userId);
	
	@Query("SELECT r FROM Restaurent r WHERE lower(r.name) LIKE lower(concat('%',:query,'%')) "+
	"OR lower(r.cuisineType) LIKE lower(concat('%',:query,'%'))")
	List<Restaurent> findBySearchQuery(String query);
}
