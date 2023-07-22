package com.adventure.service;

import java.util.List;

import com.adventure.model.Admin;
import com.adventure.model.Customer;

public interface CustomerServiceInterface {

	
	public Customer rsegisterCustomer(Customer customer);
	public Customer updateCustomer(Integer customerId,Customer customer);
	public void DeleteCustomer(Integer customerId);
	public List<Customer> viewAllcustomer();
	public Customer viewCustomerById(Integer customerId);
	public Customer validateCustomer(String username,String password);
}
