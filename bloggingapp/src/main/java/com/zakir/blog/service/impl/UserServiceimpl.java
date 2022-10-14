package com.zakir.blog.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zakir.blog.entities.User;
import com.zakir.blog.execptions.ResourceNotFoundException;
import com.zakir.blog.paylods.UserDto;
import com.zakir.blog.repositories.UserRepo;
import com.zakir.blog.services.UserService;

@Service
public class UserServiceimpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDto createUser(UserDto userdto) {
		User  user=this.dtoToUser(userdto);
		User  saveduser=this.userRepo.save(user);
		return this.userTodto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());
		User updateuser=this.userRepo.save(user);
		UserDto userdto1=this.userTodto(updateuser);
		return userdto1;
	}

	@Override
	public UserDto getUerbyId(Integer userId) {
	
		User user=this.userRepo.findById(userId).orElseThrow(()->new  ResourceNotFoundException("User","Id",userId));
		
		return this.userTodto(user);
	}

	@Override
	public List<UserDto> getallUsers() {
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userdtos= users.stream().map(user-> this.userTodto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		
	}
	
	
	private User dtoToUser(UserDto userDto)
	{
		
		User  user=this.modelmapper.map(userDto, User.class);
		
		
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
		
	}
	
	public UserDto userTodto(User user)
	{
		UserDto userdto=this.modelmapper.map(user, UserDto.class);
		
		
//		userdto.setId(user.getId());
//		userdto.setName(user.getName());
//		userdto.setEmail(user.getEmail());
//		userdto.setPassword(user.getPassword());
//		userdto.setAbout(user.getAbout());
		return userdto;
	}

}
