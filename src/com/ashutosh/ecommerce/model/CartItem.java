package com.ashutosh.ecommerce.model;

public class CartItem {
	
	private int productId;
    private String productName;
    private double price;
    private int quantity;
    private double totalPrice;
    
	public CartItem(int productId, String productName, double price, int quantity, double totalPrice) {
		this.productId = productId;
		this.productName = productName;
		this.price = price; 
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", productName=" + productName + ", price=" + price + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + "]";
	}
	
	
    
    

}
