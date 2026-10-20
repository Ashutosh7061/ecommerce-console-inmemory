package com.ashutosh.ecommerce.main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ashutosh.ecommerce.service.CustomerService;
import com.ashutosh.ecommerce.service.OrderService;
import com.ashutosh.ecommerce.service.ProductService;

public class EcommerceApplication {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ProductService productService = new ProductService();
		CustomerService customerService = new CustomerService();
		OrderService orderService = new OrderService(productService);

		AdminPanel admin = new AdminPanel(productService, customerService, orderService);

		CustomerPanel customer = new CustomerPanel(productService, customerService, orderService);

		System.out.println("*** WELCOME TO E-COMM APPLICATION ***");

		int choice;
		do {
			System.out.println(" ===== MAIN MENU =====");
			List<String> options = Arrays.asList("1. Admin", "2. Customer", "3. Exit");
			options.forEach(opt -> System.out.println(opt));

			System.out.println("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				admin.showMenu();
				break;
			case 2:
				customer.showMenu();
				break;
			case 3:
				System.out.println("Thanks for using our E-COMM Application. \n \t Visit Again");
				break;

			default:
				System.out.println("\n Please choose correct options.");

			}
		} while (choice != 3);
		
		sc.close();
	}
	

}
