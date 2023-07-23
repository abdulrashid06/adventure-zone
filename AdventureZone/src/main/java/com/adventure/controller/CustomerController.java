package com.adventure.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adventure.model.Customer;
import com.adventure.repository.CustomerRespository;
import com.adventure.service.CustomerServiceImplements;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/adventureZone")
public class CustomerController {

    @Autowired
    private CustomerServiceImplements cusService;

    @Autowired
    private CustomerRespository customerRepositry;
    
    @Autowired
    private PasswordEncoder pe;

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> rsegisterCustomer(@Valid @RequestBody Customer customer) {
    	customer.setRole("ROLE_"+customer.getRole().toUpperCase());
        customer.setPassword(pe.encode(customer.getPassword()));
        Customer cus = cusService.rsegisterCustomer(customer);
        return new ResponseEntity<Customer>(cus, HttpStatus.CREATED);

    }
     
    @PutMapping("/customers/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
        Customer cus = cusService.updateCustomer(customerId, customer);
        return new ResponseEntity<Customer>(cus, HttpStatus.ACCEPTED);
    }

//    public ResponseEntity<String> DeleteCustomer(Integer customerId) {
//        Customer cus = cusService.DeleteCustomer(customerId);
//        return new ResponseEntity<String>("customer is deleted", HttpStatus.ACCEPTED);
//    }

//    @GetMapping("/customers_list")
//    public ResponseEntity<List<Customer>> viewAllcustomer() {
//        List<Customer> cusList = cusService.viewAllcustomer();
//        return new ResponseEntity<List<Customer>>(cusList, HttpStatus.OK);
//    }

    @GetMapping("/cutomers/{customerId}")
    public ResponseEntity<Customer> viewCustomerById(@PathVariable Integer customerId) {
        Customer cus = cusService.viewCustomerById(customerId);
        return new ResponseEntity<Customer>(cus, HttpStatus.OK);
    }

    
    public ResponseEntity<Customer> validateCustomer(String username, String password) {
        Customer cus = cusService.validateCustomer(username, password);
        return new ResponseEntity<Customer>(cus, HttpStatus.CREATED);
    }
    
    @PostMapping("/customer/signIn")
	public ResponseEntity<Customer> logInUserHandler(Authentication auth){
		 Optional<Customer> opt= customerRepositry.findByEmail(auth.getName());
		 if(opt.isEmpty()) throw new RuntimeException("No user found");
		 Customer customer = opt.get();
		 return new ResponseEntity<>(customer, HttpStatus.OK);
	}

}
