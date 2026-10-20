package com.ashutosh.ecommerce.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.ashutosh.ecommerce.exception.ProductNotFoundException;
import com.ashutosh.ecommerce.model.Product;
import com.ashutosh.ecommerce.repository.ProductRepository;

public class ProductService {

	private ProductRepository productRepository;

	public ProductService() {
		productRepository = new ProductRepository();
	}

	public void addProduct(Product product) {
		int id = productRepository.generateNextProductId();
		product.setProductId(id);

		productRepository.addProduct(product);
	}

	public List<Product> getAllproducts() {

		List<Product> pro = productRepository.getAllProducts();
		Collections.sort(pro);
		return pro;
	}

	public Product getProductById(int id) {
		Product product = productRepository.getProductById(id);

		if (product == null) {
			throw new ProductNotFoundException("Product not found with given id: " + id);
		}
		return product;
	}

	public boolean deleteProduct(int id) {
		return productRepository.deleteProduct(id);
	}

	public boolean updatePrice(int id, double newPrice) {

		Product product = productRepository.getProductById(id);

		if (product != null) {
			product.setPrice(newPrice);
			return true;
		}
		return false;
	}

	public boolean updateQuantity(int id, int quantity) {

		Product product = productRepository.getProductById(id);

		if (product != null) {
			product.setQuantity(product.getQuantity() + quantity);
			return true;
		}
		return false;
	}

	public List<Product> searchByName(String name) {
		return productRepository.getAllProducts().stream()
				.filter(product -> product.getProductName().toLowerCase().contains(name.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Product> searchByCategory(String category) {
		return productRepository.getAllProducts().stream()
				.filter(product -> product.getCategory().toLowerCase().contains(category.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Product> searchByBrand(String brand) {
		return productRepository.getAllProducts().stream()
				.filter(product -> product.getBrand().toLowerCase().contains(brand.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
		return productRepository.getAllProducts().stream()
				.filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
				.collect(Collectors.toList());
	}

	public boolean reduceProduct(int productId, int quantity) {

		Product product = productRepository.getProductById(productId);

		if (product != null && product.getQuantity() >= quantity) {
			product.setQuantity(product.getQuantity() - quantity);
			return true;
		}
		return false;
	}

}
