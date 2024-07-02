package com.zigato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zigato.model.Orders;
import com.zigato.service.OrderService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/order/restaurent/{id}")
	public ResponseEntity<List<Orders>> getOrderHistoryByRest(@PathVariable long id,
			@RequestParam(required = false) String orderStatus, @RequestHeader("Authorization") String jwt) {

		List<Orders> orders = orderService.getRestOrders(id, orderStatus);

		return ResponseEntity.ok(orders);
	}

	@PutMapping("/order/{id}/status")
	public ResponseEntity<Orders> updateOrderStatus(@PathVariable long id, @RequestParam String orderStatus,
			@RequestHeader("Authorization") String jwt) {

		Orders order = orderService.updateOrder(id, orderStatus);

		return ResponseEntity.ok(order);
	}

}
