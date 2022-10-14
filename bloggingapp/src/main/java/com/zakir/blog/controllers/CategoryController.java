package com.zakir.blog.controllers;

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

import com.zakir.blog.paylods.ApiResponse;
import com.zakir.blog.paylods.CategoryDto;
import com.zakir.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	
	@Autowired
	private CategoryService  categoryservice;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid@RequestBody  CategoryDto  categorydto)
	{
		
		
		CategoryDto  createCategorydto=this.categoryservice.createcategory(categorydto);
		
		return new ResponseEntity<CategoryDto>(createCategorydto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto>updateCategory(@RequestBody CategoryDto  categorydto, @PathVariable Integer catId)
	{
		
		CategoryDto  updateCategoryDto=this.categoryservice.Update(categorydto, catId);
		return new ResponseEntity<CategoryDto>(updateCategoryDto,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@RequestBody @PathVariable  Integer catId)
	{
		this.categoryservice.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted succesfully ",true),HttpStatus.OK);
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getSingleUser( @PathVariable Integer catId)
	{
		return ResponseEntity.ok(this.categoryservice.getCategory(catId));
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getallUser()
	
	{
	  return ResponseEntity.ok(this.categoryservice.getallCategory());
	}
	
	
	

}
