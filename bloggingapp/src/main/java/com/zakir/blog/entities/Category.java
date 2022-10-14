package com.zakir.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer CategoryId;
	
	@Column(name = "title")
	private String Categorytitle;
	
	@Column(name="description")
	private String Categorydescription;
	
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();

}
