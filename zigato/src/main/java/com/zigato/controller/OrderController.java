package com.zigato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.zigato.model.Orders;
import com.zigato.model.User;
import com.zigato.request.OrderReq;
import com.zigato.response.PaymentResponse;
import com.zigato.service.OrderService;
import com.zigato.service.PaymentService;
import com.zigato.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/order")
	public ResponseEntity<PaymentResponse> createOrder(@RequestBody OrderReq req,
			@RequestHeader("Authorization") String jwt) throws StripeException {

		User user = userService.findUserByJwtToken(jwt);
		Orders order = orderService.createOrder(req, user);
		PaymentResponse res = paymentService.createPaymentLink(order);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/order/user")
	public ResponseEntity<List<Orders>> getOrderHistory(@RequestHeader("Authorization") String jwt) {

		User user = userService.findUserByJwtToken(jwt);
		List<Orders> orders = orderService.getUserOrder(user.getId());

		return ResponseEntity.ok(orders);
	}

}
