package com.zakir.blog.paylods;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "name must be more than 4 characters")
	private String name;
	
     @Email(message = "invalid email")
	private String email;
	
	@NotEmpty
	@Size(min = 3, max= 10,message = "password must be min 4 max 8 characters")
	private String password;
	
	@NotEmpty
	private String about;
	
	

}
