package com.ashutosh.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;

import com.ashutosh.ecommerce.model.Order;

public class OrderRepository {

	private List<Order> orders = new ArrayList<Order>();

	private int nextOrderId = 1;

	public int generateNextOrderId() {
		return nextOrderId++;
	}

	public void saveOrder(Order order) {
		orders.add(order);
	}

	public List<Order> getAllOrders() {
		return orders;
	}

	public List<Order> getOrderByCustomerId(int customerId) {

		List<Order> myOrders = new ArrayList<Order>();

		for (Order order : orders) {
			if (order.getCustomerId() == customerId) {
				myOrders.add(order);
			}
		}

		return myOrders;
	}

}
