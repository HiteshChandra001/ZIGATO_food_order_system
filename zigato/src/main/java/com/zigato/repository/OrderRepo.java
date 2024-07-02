package com.zigato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zigato.model.Orders;

public interface OrderRepo extends JpaRepository<Orders, Long> {
	public List<Orders> findByCustomerId(long userid);

	public List<Orders> findByRestaurentId(long rid);

}
