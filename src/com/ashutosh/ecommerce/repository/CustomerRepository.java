package com.ashutosh.ecommerce.repository;

import java.util.ArrayList;
import java.util.List;

import com.ashutosh.ecommerce.model.Customer;

public class CustomerRepository {

	private List<Customer> customers = new ArrayList<>();

	private int nextCustomerId = 1;

	public int generateCustomerId() {
		return nextCustomerId++;
	}

	public void registerCustomer(Customer customer) {
		customers.add(customer);
	}

	public List<Customer> getAllCustomers() {
		return customers;
	}

	public Customer getCustomerById(int customerId) {

		for (Customer customer : customers) {
			if (customer.getCustomerId() == customerId) {
				return customer;
			}
		}
		return null;
	}

	public boolean existsByMobile(String mobile) {

		for (Customer customer : customers) {
			if (customer.getMobile().equals(mobile)) {
				return true;
			}
		}
		return false;
	}
	
	public Customer getCustomerByMobile(String mobile) {

	    for (Customer customer : customers) {
	        if (customer.getMobile().equals(mobile)) {
	            return customer;
	        }
	    }

	    return null;
	}
}