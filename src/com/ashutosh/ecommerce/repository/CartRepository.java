package com.ashutosh.ecommerce.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.ashutosh.ecommerce.model.CartItem;

public class CartRepository {

	private Map<Integer, List<CartItem>> carts = new ConcurrentHashMap<>();

	public void addToCart(int customerId, CartItem cartItem) {

		List<CartItem> cartItems = carts.get(customerId); // to get cart, if exists

		if (cartItems == null) {
			cartItems = new ArrayList<>();
			carts.put(customerId, cartItems);
		}

		cartItems.add(cartItem);
	}

	public List<CartItem> getCart(int customerId) {

		List<CartItem> cartItems = carts.get(customerId);

		if (cartItems != null) {
			return cartItems;
		}

		return new ArrayList<>();
	}

	public boolean removeFromCart(int customerId, int productId) {

		List<CartItem> cartItems = carts.get(customerId);

		if (cartItems == null) {
			return false;
		}

		Iterator<CartItem> iterator = cartItems.iterator();

		while (iterator.hasNext()) {
			CartItem item = iterator.next();

			if (item.getProductId() == productId) {
				iterator.remove();
				return true;
			}
		}

		return false;
	}

	public void clearCart(int customerId) {
		carts.remove(customerId);
	}

	public boolean increaseQuantity(int customerId, int productId) {

		List<CartItem> cartItems = carts.get(customerId);

		if (cartItems == null) {
			return false;
		}

		for (CartItem item : cartItems) {
			if (item.getProductId() == productId) {
				item.setQuantity(item.getQuantity() + 1);
				return true;
			}
		}
		return false;
	}

	public boolean decreaseQuantity(int customerId, int productId) {

		List<CartItem> cartItems = carts.get(customerId);

		if (cartItems == null) {
			return false;
		}
		Iterator<CartItem> iterator = cartItems.iterator();

		while (iterator.hasNext()) {
			CartItem item = iterator.next();
			if (item.getProductId() == productId) {
				if (item.getQuantity() > 1) {
					item.setQuantity(item.getQuantity() - 1);
				} else {
					iterator.remove();
				}
				return true;
			}
		}
		return false;
	}

}