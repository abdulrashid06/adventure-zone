package com.adventure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adventure.model.Category;
import com.adventure.service.CategoryServiceImplements;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/adventureZone")
public class CategoryController {
	
	@Autowired
    private CategoryServiceImplements catService;
    
    
    @PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
		
		return new ResponseEntity<Category>(catService.addCategory(category),HttpStatus.CREATED);
		
	}
    
    
//    @PostMapping("/categories/{activityId}")
// 	public ResponseEntity<Category> addCategoryActivity(@Valid @RequestBody Category category,@PathVariable Integer activityId){
// 		
// 		return new ResponseEntity<Category>(catService.addCategoryWithActivity(category,activityId),HttpStatus.CREATED);
// 		
// 	}
    
    
	@PutMapping("/categories/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Integer categoryId,@RequestBody Category category){
		
		return new ResponseEntity<Category>(catService.updateCategory(categoryId,category),HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping("/categories/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId){
		catService.DeleteCategory(categoryId);
		return new ResponseEntity<String>("Category Deleted Successfully",HttpStatus.ACCEPTED);
	}
    
	@GetMapping("/categoryList")
	public ResponseEntity<List<Category>> getCategory(){
		return new ResponseEntity<List<Category>>(catService.viewAllcategory(),HttpStatus.OK);
	}
}
