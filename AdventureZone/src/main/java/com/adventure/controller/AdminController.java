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

import com.adventure.model.Admin;
import com.adventure.model.Customer;
import com.adventure.repository.AdminRespository;
import com.adventure.service.AdminServiceImplements;
import com.adventure.service.CustomerServiceImplements;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adventureZone")
public class AdminController {

    @Autowired
    private AdminServiceImplements adminService;
    
    @Autowired
    private CustomerServiceImplements cusService;
    
    @Autowired
    private AdminRespository adminRepositry;
    
    @Autowired
    private PasswordEncoder pe;

    @PostMapping("/addAdmins")
    public ResponseEntity<Admin> rsegisterAdmin(@Valid @RequestBody Admin customer) {
    	customer.setRole("ROLE_"+customer.getRole().toUpperCase());
        customer.setPassword(pe.encode(customer.getPassword()));
        Admin cus = adminService.registerAdmin(customer);
        return new ResponseEntity<Admin>(cus, HttpStatus.CREATED);

    }
    
    @PutMapping("/admins/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer adminId, @RequestBody Admin admin) {
    	Admin adm= adminService.updateAdmin(adminId, admin);
        return new ResponseEntity<Admin>(adm, HttpStatus.ACCEPTED);
    }

//    public ResponseEntity<String> DeleteCustomer(Integer adminId) {
//        Admin cus = adminService.DeleteAdmin(adminId);
//        return new ResponseEntity<String>("customer is deleted", HttpStatus.ACCEPTED);
//    }

    @GetMapping("/admins/customers_list")
    public ResponseEntity<List<Customer>> viewAllcustomer() {
        List<Customer> cusList = cusService.viewAllcustomer();
        return new ResponseEntity<List<Customer>>(cusList, HttpStatus.OK);
    }

    
    @PostMapping("/admin/signIn")
	public ResponseEntity<Admin> logInUserHandler(Authentication auth){
		 Optional<Admin> opt= adminRepositry.findByEmail(auth.getName());
		 if(opt.isEmpty()) throw new RuntimeException("No user found") ;
		 Admin admin = opt.get();
		 return new ResponseEntity<>(admin, HttpStatus.OK);
	}
    
}
