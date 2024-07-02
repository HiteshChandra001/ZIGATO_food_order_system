package com.zigato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {
	public Cart findByCustomerId(long id);
}
