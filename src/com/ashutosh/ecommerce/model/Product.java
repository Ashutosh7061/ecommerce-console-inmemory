package com.ashutosh.ecommerce.model;

public class Product  implements Comparable<Product>{
	private int  productId;
	private String productName;
	private String category;
	private double price;
	private int quantity;
	private String brand;
	
	public Product(int productId, String productName, String category, double price, int quantity, String brand) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.brand = brand;
	}
	
	public Product(String productName, String category, double price, int quantity, String brand) {
		super();
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.brand = brand;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category
				+ ", price=" + price + ", quantity=" + quantity + ", brand=" + brand + "]";
	}
	
	@Override
	public int compareTo(Product other) {
		return Integer.compare(this.productId, other.productId);
	}
	
	

}
