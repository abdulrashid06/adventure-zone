package com.adventure.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adventure.exception.AdminException;
import com.adventure.exception.CustomerException;
import com.adventure.exception.NoRecordFoundException;
import com.adventure.model.Activity;
import com.adventure.model.Category;
import com.adventure.repository.ActivityRespository;
import com.adventure.repository.CategoryRespository;

import jakarta.validation.Valid;


@Service
public class CategoryServiceImplements implements CategoryServiceInterface {

	@Autowired
	private CategoryRespository categoryRepositry;
	
	
	@Autowired
	private ActivityRespository activityRepositry;
	
	@Override
	public Category addCategory(Category category) {
		if(category==null) throw new CustomerException("The ticket you have provided is null");
		Optional<Category> cat = categoryRepositry.findById(category.getCategoryId());
		if(cat.isPresent()) throw new CustomerException("Category already exists");
		return categoryRepositry.save(category);
	}

	@Override
	public Category updateCategory(Integer categoryId, Category category) {
		Category cat = categoryRepositry.findById(categoryId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+categoryId));
		if(cat.isDeleted()==true) throw new CustomerException("Category is deleted");

		cat.setCatName(category.getCatName());
		// cat.setPrice(category.getPrice());
		return categoryRepositry.save(cat);
	}

	@Override
	public void DeleteCategory(Integer categoryId) {
		Category category = categoryRepositry.findById(categoryId).orElseThrow(() -> new NoRecordFoundException("No record found with the given id "+categoryId));
		if(category.isDeleted()==true) throw new AdminException("Category is already deleted");
		category.setDeleted(true);

	}

	@Override
	public List<Category> viewAllcategory() {
		List<Category> ticket = categoryRepositry.findAll();
		if(ticket.isEmpty()) throw new NoRecordFoundException("Category list is empty");
		return ticket;
	}

	@Override
	public Category addCategoryWithActivity(@Valid Category category, Integer activityId) {
		Optional<Activity> ac= activityRepositry.findById(activityId);
		if(ac==null) throw new CustomerException("The Activity you have provided is null");
		
		if(category==null) throw new CustomerException("The ticket you have provided is null");
		Optional<Category> cat = categoryRepositry.findById(category.getCategoryId());
		if(cat.isPresent()) throw new CustomerException("Category already exists");
		
		category.getActivities().add(ac.get());
		
		return categoryRepositry.save(category);
		
	}
}
