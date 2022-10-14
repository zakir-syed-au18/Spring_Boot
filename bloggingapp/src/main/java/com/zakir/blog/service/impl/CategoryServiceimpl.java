package com.zakir.blog.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.zakir.blog.entities.Category;
import com.zakir.blog.execptions.ResourceNotFoundException;
import com.zakir.blog.paylods.CategoryDto;
import com.zakir.blog.repositories.CategoryRepo;
import com.zakir.blog.services.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryrepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createcategory(CategoryDto categorydto) {
		
		Category cat=this.modelmapper.map(categorydto, Category.class);
		Category catadd=this.categoryrepo.save(cat);
		
		
		return this.modelmapper.map(catadd, CategoryDto.class);
	}

	@Override
	public CategoryDto Update(CategoryDto categorydto, Integer categoryId) {
		
		Category cat=this.categoryrepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category Id", categoryId));
		
		cat.setCategorytitle(categorydto.getCategorytitle());
		cat.setCategorydescription(categorydto.getCategorydescription());
		
		Category  updatecat=this.categoryrepo.save(cat);
		
		return this.modelmapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(Integer CategoryId) {
	Category cat=this.categoryrepo.findById(CategoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", CategoryId));
		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
     Category cat=categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Catergory", "category Id", categoryId));
     this.categoryrepo.delete(cat);
		
	}

	@Override
	public List<CategoryDto> getallCategory() {
		
		List<Category> categories=this.categoryrepo.findAll();
		
	List<CategoryDto> categorydtos=	categories.stream().map((cat)->this.modelmapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return categorydtos;
		// TODO Auto-generated method stub
		
	}


}
