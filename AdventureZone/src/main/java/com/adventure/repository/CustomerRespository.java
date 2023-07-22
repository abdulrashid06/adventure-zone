package com.adventure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adventure.model.Customer;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Integer> {
	
	Optional<Customer> findByEmail(String email);


}
