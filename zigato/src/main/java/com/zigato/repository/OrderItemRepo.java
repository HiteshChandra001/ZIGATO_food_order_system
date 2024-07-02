package com.zigato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

}
