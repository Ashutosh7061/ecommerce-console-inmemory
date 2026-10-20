package com.ashutosh.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ashutosh.ecommerce.exception.InsufficientStockException;
import com.ashutosh.ecommerce.exception.InvalidOrderException;
import com.ashutosh.ecommerce.model.CartItem;
import com.ashutosh.ecommerce.model.Order;
import com.ashutosh.ecommerce.model.OrderStatus;
import com.ashutosh.ecommerce.model.Product;
import com.ashutosh.ecommerce.repository.OrderRepository;

public class OrderService {

	private OrderRepository orderRepository;
	private ProductService productService;

	public OrderService(ProductService productService) {
		orderRepository = new OrderRepository();
		this.productService = productService;
	}

	public Order placeOrder(int customerId, List<CartItem> items) {
		
		if (items == null || items.isEmpty()) {
		    throw new InvalidOrderException("Your cart is empty: Cannot place an order with an empty cart.");
		}

		int orderId = orderRepository.generateNextOrderId();

		double totalAmount = 0;

		for (CartItem item : items) {

			Product product = productService.getProductById(item.getProductId());

			if (item.getQuantity() > product.getQuantity()) {
				throw new InsufficientStockException("Insufficient product found for : "+ product.getProductName());
			}
		}

		for (CartItem item : items) {
			productService.reduceProduct(item.getProductId(), item.getQuantity());
			totalAmount += item.getTotalPrice();
		}

		Order order = new Order(orderId, customerId, items, totalAmount, OrderStatus.PLACED, LocalDateTime.now());

		orderRepository.saveOrder(order);

		return order;
	}

	
	public List<Order> getOrdersByCustomerId(int customerId) {
		return orderRepository.getOrderByCustomerId(customerId);
	}

	
	public List<Order> getAllOrders(){
		return orderRepository.getAllOrders();
	}
	
	
	public boolean cancelOrder(int customerId, int orderId) {

		List<Order> individualOrder = orderRepository.getOrderByCustomerId(customerId);
		
		if(individualOrder.isEmpty()) {
			System.out.println("You have not placed any order yet..");
			return false;
		}

		for (Order order : individualOrder) {
			if (order.getOrderId() == orderId) {
				
				if(order.getOrderStatus() == OrderStatus.CANCELLED) {
					return false;
				}
				
				for (CartItem item : order.getCartItems()) {
					productService.updateQuantity(item.getProductId(), item.getQuantity());
				}
				
				order.setOrderStatus(OrderStatus.CANCELLED);
				return true;
			}
		}
		return false;
	}

}
