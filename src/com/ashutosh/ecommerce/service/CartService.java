package com.ashutosh.ecommerce.service;

import java.util.List;

import com.ashutosh.ecommerce.model.CartItem;
import com.ashutosh.ecommerce.repository.CartRepository;

public class CartService {

	private CartRepository cartRepository;

	public CartService() {
		cartRepository = new CartRepository();
	}

	public void addToCart(int customerId, CartItem cartItem) {
		cartRepository.addToCart(customerId, cartItem);
	}

	public List<CartItem> getCart(int customerId) {
		return cartRepository.getCart(customerId);
	}

	public boolean removeProduct(int customerId, int productid) {
		return cartRepository.removeFromCart(customerId, productid);
	}

	public void clearCart(int customerId) {
		cartRepository.clearCart(customerId);
	}

	public boolean increaseQuantity(int customerId, int productId) {
		return cartRepository.increaseQuantity(customerId, productId);
	}

	public boolean decreaseQuantity(int customerId, int productId) {
		return cartRepository.decreaseQuantity(customerId, productId);
	}
}
