package com.ashutosh.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

	private int orderId;
	private int customerId;
	private List<CartItem> cartItems;
	private double totalAmount;
	private OrderStatus orderStatus;
	private LocalDateTime orderDate;

	public Order(int orderId, int customerId, List<CartItem> cartItems, double totalAmount, OrderStatus orderStatus,
			LocalDateTime orderDate) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.cartItems = cartItems;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append("\n===== Order Details =====\n");
		result.append("Order ID      : ").append(orderId).append("\n");
		result.append("Customer ID   : ").append(customerId).append("\n");
		result.append("Order Status  : ").append(orderStatus).append("\n");
		result.append("Order Date    : ").append(orderDate).append("\n");
		result.append("Total Amount  : ").append(totalAmount).append("\n");

		result.append("\nItems:\n");

		for (CartItem item : cartItems) {

			result.append("  Product ID   : ").append(item.getProductId()).append("\n");
			result.append("  Product Name : ").append(item.getProductName()).append("\n");
			result.append("  Price        : ").append(item.getPrice()).append("\n");
			result.append("  Quantity     : ").append(item.getQuantity()).append("\n");
			result.append("  Total Price  : ").append(item.getTotalPrice()).append("\n");
			result.append("-------------------------\n");
		}

		return result.toString();
	}

}
