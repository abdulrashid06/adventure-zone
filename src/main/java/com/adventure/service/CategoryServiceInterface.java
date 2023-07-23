package com.adventure.service;

import java.util.List;

import com.adventure.model.Category;


public interface CategoryServiceInterface {

	
	public Category addCategory(Category category);
	public Category updateCategory(Integer categoryId,Category category);
	public void DeleteCategory(Integer categoryId);
	public List<Category> viewAllcategory();
	
	
//	public Category addCategoryWithActivity(Category category,Integer activityId);
}
