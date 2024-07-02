package com.zigato.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zigato.model.Cart;
import com.zigato.model.CartItem;
import com.zigato.model.Food;
import com.zigato.model.User;
import com.zigato.repository.CartItemRepo;
import com.zigato.repository.CartRepo;
import com.zigato.repository.FoodRepo;
import com.zigato.request.AddCartItemReq;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private FoodRepo foodRepo;
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private CartItemRepo cartItemRepo;
	@Autowired
	private UserService userService;

	@Override
	public CartItem addItemToCart(AddCartItemReq req, String jwt) {
		User user = userService.findUserByJwtToken(jwt);
		Food food = foodRepo.findById(req.getFoodId()).orElseThrow(() -> new RuntimeException("invalid food id"));
		Cart cart = cartRepo.findByCustomerId(user.getId());

		for (CartItem item : cart.getItem()) {
			if (item.getFood().equals(food)) {
				int newQuantity = item.getQuantity() + req.getQuantity();
				return updateItemQuantity(item.getId(), newQuantity);
			}
		}

		CartItem item = new CartItem();
		item.setFood(food);
		item.setCart(cart);
		item.setQuantity(req.getQuantity());
		item.setIngredients(req.getIngredients());
		item.setTotalPrice(req.getQuantity() * food.getPrice());

		CartItem savedCI = cartItemRepo.save(item);
		cart.getItem().add(savedCI);
		cartRepo.save(cart);

		return savedCI;

	}

	@Override
	public CartItem updateItemQuantity(long cartItemId, int quantity) {
		CartItem item = cartItemRepo.findById(cartItemId)
				.orElseThrow(() -> new RuntimeException("invalid cartitem id"));
		item.setQuantity(quantity);
		item.setTotalPrice(quantity * item.getFood().getPrice());
		return cartItemRepo.save(item);
	}

	@Override
	public Cart removeItemFromCart(long cartItemId, String jwt) {
		User user = userService.findUserByJwtToken(jwt);
		Cart cart = cartRepo.findByCustomerId(user.getId());
		CartItem item = cartItemRepo.findById(cartItemId)
				.orElseThrow(() -> new RuntimeException("invalid cartitem id"));
		cart.getItem().remove(item);
		return cartRepo.save(cart);
	}

	@Override
	public long calculateCartTotal(Cart cart) {
		Long total = cart.getItem().stream()
				.collect(Collectors.summingLong(item -> item.getQuantity() * item.getFood().getPrice()));
		return total;

	}

	@Override
	public Cart findCartById(long id) {
		Cart cart = cartRepo.findById(id).orElseThrow(() -> new RuntimeException("invalid cart id"));
		return cart;
	}

	@Override
	public Cart findCartByUser(String jwt) {
		User user = userService.findUserByJwtToken(jwt);
		Cart cart = cartRepo.findByCustomerId(user.getId());
		return cart;
	}

	@Override
	public Cart clearCart(String jwt) {
		Cart cart = findCartByUser(jwt);
		cart.getItem().clear();
		return cartRepo.save(cart);
	}

	@Override
	public Cart findCartByUserId(long id) {
		Cart cart = cartRepo.findByCustomerId(id);
		cart.setTotal(calculateCartTotal(cart));
		
		return cartRepo.save(cart);
	}

}
