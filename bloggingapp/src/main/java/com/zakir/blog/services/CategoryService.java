package com.zakir.blog.services;

import java.util.List;

import com.zakir.blog.paylods.CategoryDto;

public interface CategoryService {
	
	CategoryDto createcategory(CategoryDto categorydto);
	
	CategoryDto  Update(CategoryDto categorydto, Integer categoryId);
	
	CategoryDto  getCategory(Integer CategoryId);
	
	void deleteCategory(Integer categoryId);
	
	List<CategoryDto> getallCategory();

}
