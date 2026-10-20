package com.ashutosh.ecommerce.main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ashutosh.ecommerce.comparator.ProductByBrandComparator;
import com.ashutosh.ecommerce.comparator.ProductByCategoryComparator;
import com.ashutosh.ecommerce.comparator.ProductByNameComparator;
import com.ashutosh.ecommerce.comparator.ProductByPriceComparator;
import com.ashutosh.ecommerce.exception.ProductNotFoundException;
import com.ashutosh.ecommerce.model.Customer;
import com.ashutosh.ecommerce.model.Order;
import com.ashutosh.ecommerce.model.Product;
import com.ashutosh.ecommerce.service.CustomerService;
import com.ashutosh.ecommerce.service.OrderService;
import com.ashutosh.ecommerce.service.ProductService;

public class AdminPanel {

	Scanner sc = new Scanner(System.in);

	ProductService productService;
	CustomerService customerService;
	OrderService orderService;

	public AdminPanel(ProductService productService, CustomerService customerService, OrderService orderService) {
		this.productService = productService;
		this.customerService = customerService;
		this.orderService = orderService;
	}

	public void showMenu() {
		int choice;

		do {

			System.out.println("\n ===== Welcome to Admin Panel =====");
			List<String> options = Arrays.asList("1. Add Product", "2. View Products", "3. Search Product",
					"4. Update Product", "5. Delete Product", "6. View Customers", "7. View All Orders", "8. Exit");

			options.forEach(option -> System.out.println(option));

			System.out.print("Enter your choice:");
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				addProducts();
				break;

			case 2:
				getAllProducts();
				break;

			case 3:
				searchProducts();
				break;

			case 4:
				updateProduct();
				break;

			case 5:
				deleteProduct();
				break;

			case 6:
				viewAllUsers();
				break;

			case 7:
				viewAllOrders();
				break;

			case 8:
				System.out.println("\t Successfully existed from Admin panel...");
				break;

			default:
				System.out.println("You choosed wrong options");
			}

		} while (choice != 8);

	}

	// =====================================================================

	public void addProducts() {
		System.out.println("\n You are here for adding new product to product pool...");
		
		sc.nextLine();
		
		System.out.print("Enter Product Name: ");
		String productName = sc.nextLine();

		System.out.print("Enter Category: ");
		String category = sc.nextLine();

		System.out.print("Enter Price: ");
		double price = sc.nextDouble();

		System.out.print("Enter Quantity: ");
		int quantity = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter Brand: ");
		String brand = sc.nextLine();

		Product product = new Product(productName, category, price, quantity, brand);

		productService.addProduct(product);
		System.out.println("~~~~~~ Product have beem successfully added.~~~~~~");
	}

	// ===========================================================

	public void getAllProducts() {
		System.out.println("\n\t\t\t\t ===== All Products are listed Below =====");
		List<Product> products = productService.getAllproducts();
//		Collections.sort(products);
		ProductByNameComparator comparator = new ProductByNameComparator();
		products.sort(comparator);
		productService.getAllproducts().forEach(pro -> System.out.println(pro));
	}

	// ===========================================================

	public void searchProducts() {
		System.out.println("\n You are here for searching the product...");

		System.out.println("\n===== SEARCH PRODUCT =====");
		System.out.println("1. Search by Name");
		System.out.println("2. Search by Category");
		System.out.println("3. Search by Brand");
		System.out.println("4. Search by Price Range");

		System.out.print("Enter your choice: ");
		int searchChoice = sc.nextInt();
		sc.nextLine();

		List<Product> searchResults = null;

		switch (searchChoice) {

		case 1:
			System.out.print("Enter Product Name: ");
			String name = sc.nextLine();
			searchResults = productService.searchByName(name);
			searchResults.sort(new ProductByNameComparator());
			break;

		case 2:
			System.out.print("Enter Category: ");
			String category1 = sc.nextLine();
			searchResults = productService.searchByCategory(category1);
			searchResults.sort(new ProductByCategoryComparator());
			break;

		case 3:
			System.out.print("Enter Brand: ");
			String brand1 = sc.nextLine();
			searchResults = productService.searchByBrand(brand1);
			searchResults.sort(new ProductByBrandComparator());
			break;

		case 4:
			System.out.print("Enter Minimum Price: ");
			double minPrice = sc.nextDouble();
			System.out.print("Enter Maximum Price: ");
			double maxPrice = sc.nextDouble();
			searchResults = productService.searchByPriceRange(minPrice, maxPrice);
			searchResults.sort(new ProductByPriceComparator());
			break;

		default:
			System.out.println("Invalid search choice.");

		}
		if (searchResults != null) {
			if (searchResults.isEmpty()) {
				System.err.println("      No product found.");
			} else {
				System.out.println("\n===== SEARCH RESULTS =====");
				searchResults.forEach(pro -> System.out.println(pro));
			}
		}

	}

	// ===========================================================

	public void updateProduct() {
		System.out.println("\n  You are here for updating product price of qunatity");

		System.out.print("Enter product id to update: ");
		int id2 = sc.nextInt();

		try {
			System.out.println("\n What do you want to update?");
			System.out.println("1. Price");
			System.out.println("2. Quantity");
			System.out.println("3. Both Price and Quantity");
			System.out.println("4. Cancel");

			System.out.print("Enter your choice: ");
			int updateChoice = sc.nextInt();

			switch (updateChoice) {

			case 1:
				System.out.print("Enter new price: ");
				double newPrice = sc.nextDouble();

				if (productService.updatePrice(id2, newPrice)) {
					System.out.println(" ~~~~~ Price updated successfully.~~~~~");
				}
				break;

			case 2:
				System.out.print("Enter quantity to increase by: ");
				int newQuantity = sc.nextInt();

				if (productService.updateQuantity(id2, newQuantity)) {
					System.out.println(" ~~~~~ Quantity updated successfully.~~~~~");
				}
				break;

			case 3:
				System.out.print("Enter new price: ");
				double newPrice1 = sc.nextDouble();

				System.out.print("Enter quantity to increase by: ");
				int newQuantity1 = sc.nextInt();

				productService.updatePrice(id2, newPrice1);
				productService.updateQuantity(id2, newQuantity1);

				System.out.println(" ~~~~~ Price and quantity updated successfully.~~~~~");
				break;

			case 4:
				System.out.println("```Update cancelled.```");
				break;

			default:
				System.out.println("```Invalid choice.```");
			}
		} catch (ProductNotFoundException e) {

			System.out.println(e.getMessage());
		}
	}

	// ===========================================================

	public void deleteProduct() {
		System.out.println("\n  You are here to delete an item fro product pool..");

		System.out.print("Enter product id to be deleted: ");
		int id1 = sc.nextInt();
		boolean result = productService.deleteProduct(id1);

		if (result == true) {
			System.out.println("~~~~~ Product have been deleted successfully.~~~~~");
		} else {
			System.out.println("~~~~~~ Product not found or deletion issue.~~~~~~");
		}
	}

	// ===========================================================

	public void viewAllUsers() {
		System.out.println("\n You are here to view all users.");

		System.out.println("\n ===== All Customers on Platform =====");

		List<Customer> customers = customerService.getAllCustomers();

		if (customers.isEmpty()) {
			System.out.println("\n No Customers is registered to platform yet..");
			return;
		}

		customers.forEach(customer -> System.out.println(customer));
	}

	// ===========================================================

	public void viewAllOrders() {
		System.out.println("\n You are here to view all orders on platform...");

		List<Order> allOrders = orderService.getAllOrders();

		if (allOrders.isEmpty()) {
			System.out.println("\n No order has been placed yet...");
		}

		System.out.println(" ====== All Orders =====");
		allOrders.forEach(order -> System.out.println(order));
	}

}
