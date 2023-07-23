package com.adventure.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.adventure.model.Activity;
import com.adventure.model.Admin;
import com.adventure.model.Customer;

public interface AdminServiceInterface {
	
	public Admin registerAdmin(Admin admin);
	public Admin updateAdmin(Integer adminId,Admin admin);
	public void DeleteAdmin(Integer adminId);
	public List<Customer> viewAllCustomer();
	public List<Activity> viewAllCustomerActivityById(Integer customerId);
	public List<Activity> viewAllactivity();
	public List<Activity> viewAllactivityCategorywise();
	public List<Activity> viewAllactivitydatewise(LocalDate date);
	public List<Activity> viewAllTicketsforDays(LocalDateTime fromDate,LocalDateTime enddate);
	List<Admin> viewAlladmin();

}
