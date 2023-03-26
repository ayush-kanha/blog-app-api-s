package com.blog.services;

import java.util.List;

import com.blog.payloads.CategoryDto;

public interface CategoryService 
{
	//CREATE CATEGORY	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//UPDATE CATEGORY
	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	
	//DELETE CATEGORY
	void deleteCategory(int categoryId);
	
	//GET CATEGORY
	CategoryDto getCategory(int categoryId);
	
	//GET ALL CATEGORY
	List<CategoryDto> getAllCategories(); 
	
	
}
