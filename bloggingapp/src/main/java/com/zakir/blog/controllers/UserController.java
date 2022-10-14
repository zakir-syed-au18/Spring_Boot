package com.zakir.blog.controllers;

import java.util.List;
import java.util.Map;

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

import com.zakir.blog.execptions.ResourceNotFoundException;
import com.zakir.blog.paylods.ApiResponse;
import com.zakir.blog.paylods.UserDto;
import com.zakir.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto)
	{
		UserDto  createUserdto=this.service.createUser(userdto);
		return new ResponseEntity<>(createUserdto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userdto,@PathVariable Integer userId)
	{
		UserDto updateuser=this.service.updateUser(userdto, userId);
		return ResponseEntity.ok(updateuser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUer(@PathVariable Integer userId)
	{
		this.service.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getallUsers()
	{
		return ResponseEntity.ok(this.service.getallUsers());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getsingleUser(@PathVariable Integer  userId)
	{
		
		
		
	return  ResponseEntity.ok(this.service.getUerbyId(userId));
	
		

		}

}
