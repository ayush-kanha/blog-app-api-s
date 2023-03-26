package com.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.exceptions.DataIntegrityViolationException;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.exceptions.SQLIntegrityConstraintViolationException;
import com.blog.payloads.CategoryDto;
import com.blog.repositories.CategoryRepo;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	//CREATING CATEGORY
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		try {
			Category createdCategory = categoryRepo.save(dtoToCategory(categoryDto));
			return this.categoryToDto(createdCategory);
		}
		catch(Exception ex) {
			if(ex.getClass().getName().equals("org.springframework.dao.DataIntegrityViolationException"))
			{
				//String categoryTitle = categoryDto.getCategoryTitle();
				throw new DataIntegrityViolationException("Title "+categoryDto.getCategoryTitle());
			}
			else
				throw new SQLIntegrityConstraintViolationException();
		}
	}

	//UPDATING CATEGORY
	
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> 
								new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		categoryRepo.save(category);
		
		return categoryToDto(category);
	}

	//DELETING CATEGORY
	
	@Override
	public void deleteCategory(int categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new 
											ResourceNotFoundException("Category", "categoryId", categoryId));
		
		categoryRepo.delete(category);
		
	}
	
	
	//FETCHING A CATEGORY

	@Override
	public CategoryDto getCategory(int categoryId) {

		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new 
				ResourceNotFoundException("Category", "categoryId", categoryId));
		
		return categoryToDto(category);
	}

	//FETCHING ALL CATEGORIES
	
	@Override
	public List<CategoryDto> getAllCategories() {
		
		List<Category> categories = categoryRepo.findAll();
		
		List<CategoryDto> dto = categories.stream().
								map(category -> categoryToDto(category)).collect(Collectors.toList());
		
		
		
		return dto;
	}
	
	//Method to map categoryDto to Category
	
	public Category dtoToCategory(CategoryDto categoryDto)
	{
		Category category = mapper.map(categoryDto,Category.class);
		return category;
	}
	
	
	//Method to map Category to categoryDto 
	
	public CategoryDto categoryToDto(Category category)
	{
		CategoryDto categoryDto = this.mapper.map(category, CategoryDto.class);		
		return categoryDto;
	}
	
	
	
	
	
	
	
	

}
