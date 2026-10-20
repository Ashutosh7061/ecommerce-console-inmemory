package com.ashutosh.ecommerce.service;

import java.util.List;

import com.ashutosh.ecommerce.exception.CustomerNotFoundException;
import com.ashutosh.ecommerce.model.Customer;
import com.ashutosh.ecommerce.repository.CustomerRepository;

public class CustomerService {

	 private CustomerRepository customerRepository;

	public CustomerService() {
		customerRepository = new CustomerRepository();
	}

	public boolean registerCustomer(Customer customer) {

		if (customerRepository.existsByMobile(customer.getMobile())) {
			return false;
		}

		int customerId = customerRepository.generateCustomerId();
		
		customer.setCustomerId(customerId);
		customerRepository.registerCustomer(customer);
		return true;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}

	public Customer getCustomerById(int customerId) {

		Customer customer = customerRepository.getCustomerById(customerId);

		if (customer == null) {
			throw new CustomerNotFoundException("Customer not found with the given is: " + customerId);
		}

		return customer;
	}

	public Customer login(String mobile) {
		return customerRepository.getCustomerByMobile(mobile);
	}

	public boolean updateAddress(int customerId, String address) {

		Customer customer = getCustomerById(customerId);

		customer.setAddress(address);

		return true;
	}
	
	

}