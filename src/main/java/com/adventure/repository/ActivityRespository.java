package com.adventure.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adventure.model.Activity;


public interface ActivityRespository  extends JpaRepository<Activity, Integer> {
//	 List<Activity> findByDate(LocalDate date);

//	List<Activity> findByCustomerAndDateBetween(Integer customerId, LocalDateTime fromDate, LocalDateTime endDate);

//	List<Activity> findAllByCustomerId(Integer customerId);
	
}
