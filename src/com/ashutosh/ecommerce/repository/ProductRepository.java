package com.ashutosh.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;

import com.ashutosh.ecommerce.model.Product;

public class ProductRepository {

	private List<Product> products = new ArrayList<>();
	
	private int nextProductId = 5;
	
	public int generateNextProductId() {
		return nextProductId++;
	}

	public void addInitialProducts() {
		products.add(new Product(1, "iPhone 16", "Mobile", 79999, 10, "Apple"));
		products.add(new Product(2, "Galaxy S25", "Mobile", 69999, 15, "Samsung"));
		products.add(new Product(3, "Inspiron 15", "Laptop", 65000, 8, "Dell"));
		products.add(new Product(4, "WH-1000XM5", "Headphones", 29999, 12, "Sony"));
	}
	
	public ProductRepository() {
	    addInitialProducts();
	}

	public void addProduct(Product product) {
		products.add(product);
	}

	public List<Product> getAllProducts() {
		return products;
	}

	public Product getProductById(int id) {

		for (Product product : products) {
			if (product.getProductId() == id) {
				return product;
			}
		}
		return null;
	}

	public boolean deleteProduct(int id) {

		Product product = getProductById(id);

		if (product != null) {
			products.remove(product);
			return true;
		}

		return false;
	}

}
