package com.blog.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.blog.payloads.ApiResponse;
import com.blog.payloads.CategoryDto;
import com.blog.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	public CategoryServiceImpl categoryServiceImpl;
	
	//CREATE A CATEGORY
	
	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory = categoryServiceImpl.createCategory(categoryDto);
		
		return new ResponseEntity<>(createCategory,HttpStatus.CREATED);
	}
	
	//UPDATE A CATEGORY
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
														@PathVariable("categoryId") int categoryId)
	{
		CategoryDto updatedCategory = categoryServiceImpl.updateCategory(categoryDto, categoryId);
		
		return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
	}
	
	
	//GET A CATEGORY BY CATEGORY_ID
	
	@GetMapping("/get/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") int categoryId)
	{
		CategoryDto singleCategory = categoryServiceImpl.getCategory(categoryId);
		
		return new ResponseEntity<CategoryDto>(singleCategory,HttpStatus.OK);
	}
	
	//DELETE A CATEGORY BY CATEGORY_ID
	
	@DeleteMapping("delete/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int categoryId)
	{
		categoryServiceImpl.deleteCategory(categoryId);
		
		return new ResponseEntity<>(new ApiResponse("Category deleted Successfully.",true),HttpStatus.FOUND);
	}
	
	// GET ALL CATEGORIES
	
	@GetMapping("/getAllcategories")
	public ResponseEntity<List<CategoryDto>> getAllCategories()
	{
		List<CategoryDto> allCategories = categoryServiceImpl.getAllCategories();
		
		return new ResponseEntity<List<CategoryDto>>(allCategories,HttpStatus.FOUND);
	}
	
	
	
}
