package com.adventure.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adventure.exception.CustomerException;
import com.adventure.exception.NoRecordFoundException;
import com.adventure.model.Activity;
import com.adventure.model.Admin;
import com.adventure.model.Customer;
import com.adventure.repository.ActivityRespository;
import com.adventure.repository.AdminRespository;
import com.adventure.repository.CustomerRespository;




@Service
public class AdminServiceImplements implements AdminServiceInterface {

	@Autowired
	private AdminRespository adminRepositry;
	
	@Autowired
	private CustomerRespository customerRepositry;
	@Autowired
	private ActivityRespository activityRepo;
	@Autowired
	private PasswordEncoder pe;

	@Override
	public Admin registerAdmin(Admin admin) {
		// TODO Auto-generated method stub
		if(admin==null) throw new CustomerException("The Admin you have provided is null");
		Optional<Admin> ad = adminRepositry.findByEmail(admin.getEmail());
		if(ad.isPresent()) throw new CustomerException("Admin already exists");
		return adminRepositry.save(admin);
	}


	@Override
	public Admin updateAdmin(Integer adminId, Admin admin) {
		Admin ad = adminRepositry.findById(adminId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+adminId));
		if(ad.isDeleted()==true) throw new CustomerException("Admin is deleted");

		ad.setAddress(admin.getAddress());
		ad.setMobNumber(admin.getMobNumber());
		return adminRepositry.save(ad);
	}

	@Override
	public void DeleteAdmin(Integer adminId) {
		Admin admin = adminRepositry.findById(adminId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+adminId));
		if(admin.isDeleted()==true) throw new CustomerException("Admin is already deleted");
		admin.setDeleted(true);

	}

	@Override
	public List<Admin> viewAlladmin() {
		List<Admin> admin = adminRepositry.findAll();
		if(admin.isEmpty()) throw new NoRecordFoundException("Admin list is empty");
		return admin;
	}

	

	@Override
	public List<Activity> viewAllactivityCategorywise() {
	    List<Activity> actList = activityRepo.findAll();

	    // Sort the list of activities based on the category name in ascending order
	    Collections.sort(actList, new Comparator<Activity>() {
	        @Override
	        public int compare(Activity activity1, Activity activity2) {
	            return activity1.getCategory().compareTo(activity2.getCategory());
	        }
	    });
	    return actList;
	}

//	@Override
//	public List<Activity> viewAllactivitydatewise(LocalDate date) {
//		  List<Activity> activities = activityRepo.findByDate(date);
//		    if (activities.isEmpty()) throw new NoRecordFoundException("No activities found for the given date");
//		    return activities;
//	}
//
//	@Override
//	public List<Activity> viewAllactivityforDays(Integer customerId, LocalDateTime fromDate, LocalDateTime endDate) {
//		  List<Activity> activities = activityRepo.findByCustomerAndDateBetween(customerId, fromDate, endDate);
//		    if (activities.isEmpty()) throw new NoRecordFoundException("No activities found for the given customer and date range");
//
//		    return activities;
//	}
//
//	@Override
//	public List<Activity> viewAllCustomerActivityById(Integer customerId) {
//		 List<Activity> activities = activityRepo.findAllByCustomerId(customerId);
//	        if (activities.isEmpty()) throw new NoRecordFoundException("No activities found for the given customer");
//
//	        return activities;
//	}


	@Override
	public List<Customer> viewAllCustomer(){

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
	public List<Activity> viewAllactivity() {
		List<Activity> actList = activityRepo.findAll();
		if(actList.isEmpty()) throw new NoRecordFoundException("No record found");
		return actList;
	}


	@Override
	public List<Activity> viewAllCustomerActivityById(Integer customerId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Activity> viewAllactivitydatewise(LocalDate date) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Activity> viewAllactivityforDays(Integer customerId, LocalDateTime fromDate, LocalDateTime enddate) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
