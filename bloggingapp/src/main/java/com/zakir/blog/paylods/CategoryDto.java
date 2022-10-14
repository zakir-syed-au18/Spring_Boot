package com.zakir.blog.paylods;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	
	private Integer  categoryId;
	
	
	@NotEmpty
	@Size(min = 4,message = "min 4 character you have two enter")
	private String Categorytitle;
	
	@NotEmpty
	@Size(min =4,message="min 4 character")
	private  String Categorydescription;

}
