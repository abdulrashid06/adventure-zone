package com.adventure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventure.exception.CustomerException;
import com.adventure.exception.NoRecordFoundException;
import com.adventure.model.Customer;
import com.adventure.repository.CustomerRespository;


@Service
public class CustomerServiceImplements implements CustomerServiceInterface {

	@Autowired
	private CustomerRespository customerRepositry;

//	@Autowired
//	private PasswordEncoder pe;
	
	
	@Override
	public Customer rsegisterCustomer(Customer customer) {
		
		if(customer==null) throw new CustomerException("The customer you have provided is null");
		Optional<Customer> cus = customerRepositry.findByEmail(customer.getEmail());
		if(cus.isPresent()) throw new CustomerException("Customer already exists");
		
		return customerRepositry.save(customer);
	}

	@Override
	public Customer updateCustomer(Integer customerId, Customer customer) {

		Customer cus = customerRepositry.findById(customerId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+customerId));
		if(cus.isDeleted()==true) throw new CustomerException("Customer is deleted");

		cus.setAddress(customer.getAddress());
		cus.setMobNumber(customer.getMobNumber());
		
		return customerRepositry.save(cus);
	}

	@Override
	public void DeleteCustomer(Integer customerId) {

		Customer cus = customerRepositry.findById(customerId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+customerId));
		if(cus.isDeleted()==true) throw new CustomerException("Customer is already deleted");
		cus.setDeleted(true);
		
	}

	@Override
	public List<Customer> viewAllcustomer() {

		List<Customer> customers = customerRepositry.findAll();
		if(customers.isEmpty()) throw new NoRecordFoundException("Customer list is empty");
		List<Customer> cus = new ArrayList<>();
		for(Customer c : customers) {
			if(c.getRole().equals("ROLE_USER")) {
				cus.add(c);
			}
		}
		return cus;
	}

	@Override
	public Customer validateCustomer(String username, String password) {

		if(username==null || password==null) throw new CustomerException("Invalid credentials");
		Customer customer = customerRepositry.findByEmail(username).get();

//		if(pe.matches(password, customer.getPassword())){
//			return customer;
//		}else{
//			throw new CustomerException("Invalid password");
//		}
		return customer;
		
	}

	@Override
	public Customer viewCustomerById(Integer customerId) {
		Customer cus = customerRepositry.findById(customerId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+customerId));
		if(cus.isDeleted()==true) throw new CustomerException("No record found with the given id "+customerId);
		return cus;
	}

}
