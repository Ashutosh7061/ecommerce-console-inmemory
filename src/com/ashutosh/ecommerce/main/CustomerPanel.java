package com.ashutosh.ecommerce.main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ashutosh.ecommerce.comparator.ProductByBrandComparator;
import com.ashutosh.ecommerce.comparator.ProductByCategoryComparator;
import com.ashutosh.ecommerce.comparator.ProductByNameComparator;
import com.ashutosh.ecommerce.comparator.ProductByPriceComparator;
import com.ashutosh.ecommerce.exception.InsufficientStockException;
import com.ashutosh.ecommerce.exception.InvalidOrderException;
import com.ashutosh.ecommerce.exception.InvalidQuantityException;
import com.ashutosh.ecommerce.exception.ProductNotFoundException;
import com.ashutosh.ecommerce.model.CartItem;
import com.ashutosh.ecommerce.model.Customer;
import com.ashutosh.ecommerce.model.Order;
import com.ashutosh.ecommerce.model.Product;
import com.ashutosh.ecommerce.service.CartService;
import com.ashutosh.ecommerce.service.CustomerService;
import com.ashutosh.ecommerce.service.OrderService;
import com.ashutosh.ecommerce.service.ProductService;

public class CustomerPanel {

	private CustomerService customerService;
	private ProductService productService;
	private CartService cartService;
	private OrderService orderService;

	public CustomerPanel(ProductService productService, CustomerService customerService, OrderService orderService) {
		this.productService = productService;
		this.customerService = customerService;
		this.orderService = orderService;

		cartService = new CartService();
	}

	Scanner sc = new Scanner(System.in);

	Customer loggedInUser = null;

	public void showMenu() {

		int choice;

		do {
			System.out.println("\n ===== Welcome to Customer Panel =====");

			List<String> options1 = Arrays.asList("1. Register", "2. Login", "3. View Products", "4. Search Product",
					"10. Exit");

			List<String> options = Arrays.asList("1. View Products", "2. Search Product", "3. Add Product to Cart",
					"4. View Cart", "5. Remove Product from Cart", "6. Update Product Quantity", "7. Place Order",
					"8. View My Orders", "9. Cancel Order", "10. Logout");

			if (loggedInUser == null) {

				options1.forEach(option -> System.out.println(option));

			} else {

				options.forEach(option -> System.out.println(option));
			}

			System.out.print("Enter your choice: ");
			choice = sc.nextInt();

			if (loggedInUser == null) {

				switch (choice) {

				case 1:
					registerNewUser();
					break;

				case 2:
					loggin();
					break;

				case 3:
					viewAllProducts();
					break;

				case 4:
					searchProducts();
					break;

				case 10:
					System.out.println("Exiting Customer Panel...");
					break;

				default:
					System.out.println("Invalid choice.");
				}

			} else {

				switch (choice) {

				case 1:
					viewAllProducts();
					break;

				case 2:
					searchProducts();
					break;

				case 3:
					addProductToCart();
					break;

				case 4:
					viewCart();
					break;

				case 5:
					removeItemFromCart();
					break;

				case 6:
					updateProductQuantity();
					break;

				case 7:
					placeOrder();
					break;

				case 8:
					viewMyOrders();
					break;

				case 9:
					cancelOrder();
					break;

				case 10:
					loggedInUser = null;
					System.out.println("You have been logged out successfully....");
					break;

				default:
					System.err.println("Invalid choice....");
				}
			}

		} while (choice != 10);
	}

	
	
	// ===========================================================
	public void registerNewUser() {
		System.out.println("\n You are here to register as a new member...");

		System.out.print("Enter Name: ");
		sc.nextLine();
		String name = sc.nextLine();

		System.out.print("Enter Email: ");
		String email = sc.nextLine();

		System.out.print("Enter Mobile: ");
		String mobile = sc.nextLine();

		System.out.print("Enter Address: ");
		String address = sc.nextLine();

		Customer customer = new Customer(name, email, mobile, address);

		boolean registered = customerService.registerCustomer(customer);

		if (registered) {
			System.out.println("\t ~~~~~ Customer registered successfully.~~~~~");
//			System.err.println("\t\tYour Customer ID is: " + customer.getCustomerId());
		} else {
			System.out.println("~~~~~ Customer with this mobile number already exists.~~~~~");
		}

	}

	// ===========================================================

	public void loggin() {
		System.out.println("\n  You are here for logging into your profile...");
		System.out.print(" Enter your mobile number:");
		sc.nextLine();
		String mobile1 = sc.nextLine();

		loggedInUser = customerService.login(mobile1);

		if (loggedInUser != null) {
			System.out.println(
					"\t ** Welcome " + loggedInUser.getName() + "** \nYou have been successfully logged in...");
		} else {
			System.err.println("\n\t Customer not found. Please register first...");
		}
	}

	// ===========================================================

	public void viewAllProducts() {
		System.out.println("\n You are here for viewing products...");
		List<Product> products = productService.getAllproducts();

		products.forEach(pro -> System.out.println(pro));

	}

	// ===========================================================

	public void searchProducts() {
		System.out.println("\n You are here for searching product...");

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
			String name1 = sc.nextLine();
			searchResults = productService.searchByName(name1);
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

	public void addProductToCart() {
		System.out.println("\n ===== You are here to add products to yout cart =====");

//		if (loggedInUser == null) {
//			System.err.println("\n You are not logged in , login first..");
//			return;
//		}

		try {
			System.out.println(" Enter product id: ");
			int productId = sc.nextInt();

			Product product = productService.getProductById(productId);

			System.out.println("Product: " + product.getProductName());
			System.out.println("Category: " + product.getCategory());
			System.out.println("Price: " + product.getPrice());
			System.out.println("Available Qunatity in stock: " + product.getQuantity());

			System.out.println("Enter qunatity you want to ad to cart..");
			int orderQuantity = sc.nextInt();

			if (orderQuantity <= 0) {
				throw new InvalidQuantityException("Minimun order quantity must be 1.");
			}
			if (orderQuantity > product.getQuantity()) {
				System.out.println("We don't have this much quantity available right now.");
				return;
			}

			CartItem cartItem = new CartItem(product.getProductId(), product.getProductName(), product.getPrice(),
					orderQuantity, product.getPrice() * orderQuantity);

			cartService.addToCart(loggedInUser.getCustomerId(), cartItem);

			System.out.println("~~~~~ Product have been successfully added to your cart.~~~~~");
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	// ===========================================================

	public void viewCart() {
		System.out.println("\n You are here to view your cart...");

		List<CartItem> yourCart = cartService.getCart(loggedInUser.getCustomerId());

		if (yourCart.isEmpty()) {
			System.out.println("\n Your cart is empty, nothing is in your cart..");
			return;
		}
		yourCart.forEach(items -> System.out.println(items));

	}

	// ===========================================================

	public void removeItemFromCart() {
		System.out.println("\n You are here to remove an item from your cart..");

		List<CartItem> yourCartItem = cartService.getCart(loggedInUser.getCustomerId());

		if (yourCartItem.isEmpty()) {
			System.out.println("\n Your cart is empty, nothing is in your cart..");
			return;
		}

		System.out.println("\n Enter productId of product which you want to remove: ");
		int removeProduct = sc.nextInt();

		boolean result = cartService.removeProduct(loggedInUser.getCustomerId(), removeProduct);

		if (result) {
			System.out.println("\n~~~~~ Product have been successfully removed from your cart.~~~~~");
		} else {
			System.out.println("Product not found in your cart.");
		}

	}

	// ===========================================================

	public void updateProductQuantity() {
		System.out.println("\nYou are here for updating product quantity from your cart..");

		System.out.print("Enter Product ID: ");
		int productId = sc.nextInt();

		System.out.println("1. Increase Quantity");
		System.out.println("2. Decrease Quantity");
		System.out.print("Enter your choice: ");
		int quantityChoice = sc.nextInt();

		boolean updated = false;

		if (quantityChoice == 1) {

			updated = cartService.increaseQuantity(loggedInUser.getCustomerId(), productId);

		} else if (quantityChoice == 2) {

			updated = cartService.decreaseQuantity(loggedInUser.getCustomerId(), productId);

		} else {
			System.out.println("Invalid choice.");
			return;
		}

		if (updated) {
			System.out.println("Product quantity updated successfully.");
		} else {
			System.out.println("Product not found in your cart.");
		}
	}
	// ===========================================================

	public void placeOrder() {
		System.out.println("\n You are here for placing your order...");

		try {

			List<CartItem> yourFinalCart = cartService.getCart(loggedInUser.getCustomerId());

			if (yourFinalCart.isEmpty()) {
				System.out.println("\n Your cart is empty, nothing is in your cart to place order..");
				return;
			}

			System.out.println("Your Items in your cart..");
			yourFinalCart.forEach(item -> System.out.println(item));

			Order order = orderService.placeOrder(loggedInUser.getCustomerId(), yourFinalCart);

			cartService.clearCart(loggedInUser.getCustomerId());

			System.out.println(" \n~~~~~ Your order have been placed successfully.~~~~~");
			System.out.println("\n Your order id is : " + order.getOrderId());
			System.out.println("Your totalAmount is: " + order.getTotalAmount());

		} catch (InvalidOrderException e) {
			System.out.println(e.getMessage());
		} catch (ProductNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (InsufficientStockException e) {

			System.out.println(e.getMessage());
		}
	}

	// ===========================================================

	public void viewMyOrders() {
		System.out.println("\n You are here for viewing your all orders...");

		if (loggedInUser == null) {
			System.err.println("\n You are not logged in , login first..");
			return;
		}

		List<Order> myOrders = orderService.getOrdersByCustomerId(loggedInUser.getCustomerId());

		if (myOrders.isEmpty()) {
			System.out.println("You don't have any orders yet, order now...");
		}
		System.out.println(" ===== This is Your Order History =====");

		myOrders.forEach(orders -> System.out.println(orders));
	}

	// ===========================================================

	public void cancelOrder() {

		System.out.println("\n You are here to cancel your order..");

		System.out.println("\n Enter your Order id to cancel order: ");
		int orderId = sc.nextInt();

		boolean cancellationResult = orderService.cancelOrder(loggedInUser.getCustomerId(), orderId);

		if (cancellationResult) {
			System.out.println("\n ~~~~~ Your order with id :" + orderId + ", has been successfully cancelled.~~~~~");
		} else {
			System.out.println("\n No order found with the given orderId.");
		}
	}
}
