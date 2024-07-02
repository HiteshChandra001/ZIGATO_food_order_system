package com.zigato.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zigato.model.Cart;
import com.zigato.model.CartItem;
import com.zigato.request.AddCartItemReq;
import com.zigato.request.UpdateCartItemReq;
import com.zigato.service.CartService;

@RestController
@RequestMapping("/api")
public class CartController {
	@Autowired
	private CartService cartService;

	@PostMapping("/cart/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemReq req,
			@RequestHeader("Authorization") String jwt) {
		CartItem cartItem = cartService.addItemToCart(req, jwt);
		return ResponseEntity.ok(cartItem);
	}

	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItem> updateItemQuantity(@RequestBody UpdateCartItemReq req,
			@RequestHeader("Authorization") String jwt) {
		CartItem ci = cartService.updateItemQuantity(req.getCartItemId(), req.getQuantity());
		return ResponseEntity.ok(ci);
	}

	@DeleteMapping("cart-item/{id}/remove")
	public ResponseEntity<Cart> removeItemFromCart(@PathVariable long id, @RequestHeader("Authorization") String jwt) {
		Cart cart = cartService.removeItemFromCart(id, jwt);
		return ResponseEntity.ok(cart);
	}

	@GetMapping("/cart")
	public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) {
		Cart cart = cartService.findCartByUser(jwt);
		return ResponseEntity.ok(cart);
	}

	@PutMapping("/cart/clear")
	public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) {
		Cart cart = cartService.clearCart(jwt);
		return ResponseEntity.ok(cart);
	}

}
