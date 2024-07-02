package com.zigato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.CartItem;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
