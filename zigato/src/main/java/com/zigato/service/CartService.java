package com.zigato.service;

import com.zigato.model.Cart;
import com.zigato.model.CartItem;
import com.zigato.request.AddCartItemReq;

public interface CartService {

	public CartItem addItemToCart(AddCartItemReq req, String jwt);

	public CartItem updateItemQuantity(long cartItemId, int quantity);

	public Cart removeItemFromCart(long cartItemId, String jwt);

	public long calculateCartTotal(Cart cart);

	public Cart findCartById(long id);
	
	public Cart findCartByUserId(long id);

	public Cart findCartByUser(String jwt);

	public Cart clearCart(String jwt);
}
