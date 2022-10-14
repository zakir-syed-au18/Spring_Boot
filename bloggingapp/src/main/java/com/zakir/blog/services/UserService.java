package com.zakir.blog.services;

import java.util.List;

import com.zakir.blog.paylods.UserDto;

public interface UserService {
	
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUerbyId(Integer userId);
	
	List<UserDto> getallUsers();
	
	void deleteUser(Integer userId);

}
