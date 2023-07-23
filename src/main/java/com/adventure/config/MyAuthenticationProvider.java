package com.adventure.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.adventure.exception.NoRecordFoundException;
import com.adventure.model.Admin;
import com.adventure.model.Customer;
import com.adventure.repository.AdminRespository;
import com.adventure.repository.CustomerRespository;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerRespository customerRepository;
	
	@Autowired
	private AdminRespository adminRepository;
	
	@Autowired
	private PasswordEncoder pe;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		System.out.println("Authentication provider is used...");
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
//		return customerAuthenticate(username, password);
		
		if(username.startsWith("CUST")) {
			String customerUsername = username.substring(4);
			return customerAuthenticate(customerUsername, password);
		}
		else if(username.startsWith("ADMIN")) {
			String adminUsername = username.substring(5);
			return AdminAuthenticate(adminUsername, password);
		}else {
			throw new BadCredentialsException("Invalid Username");
		}
		
		
	}
	
	
	private Authentication customerAuthenticate(String username, String password) {
		Optional<Customer> opt = customerRepository.findByEmail(username);
		if(opt.isEmpty()) {
			throw new NoRecordFoundException("No registered user found with the given username "+username);
		}
		
		Customer customer  = opt.get();
		if(pe.matches(password, customer.getPassword())) {
			List<GrantedAuthority> grantedAuthority = new ArrayList<>();
			grantedAuthority.add(new SimpleGrantedAuthority(customer.getRole()));
			return new UsernamePasswordAuthenticationToken(username, password, grantedAuthority);
		}else {
			System.out.println(username);
			throw new BadCredentialsException("Invalid credentials");
		}
	}
	
	
	private Authentication AdminAuthenticate(String username, String password) {
		Optional<Admin> opt = adminRepository.findByEmail(username);
		if(opt.isEmpty()) {
			throw new NoRecordFoundException("No registered user found with the given username "+username);
		}
		
		Admin admin = opt.get();
		if(pe.matches(password, admin.getPassword())) {
			List<GrantedAuthority> grantedAuthority = new ArrayList<>();
			grantedAuthority.add(new SimpleGrantedAuthority(admin.getRole()));
			return new UsernamePasswordAuthenticationToken(username, password, grantedAuthority);
		}else {
			throw new BadCredentialsException("Invalid credentials");
		}
	}
	
	

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);	}

}
