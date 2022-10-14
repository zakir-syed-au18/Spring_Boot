package com.zakir.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zakir.blog.config.AppConstants;
import com.zakir.blog.entities.Post;
import com.zakir.blog.paylods.ApiResponse;
import com.zakir.blog.paylods.PostDto;
import com.zakir.blog.paylods.PostResponse;
import com.zakir.blog.services.FileService;
import com.zakir.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postservice;
	
	@Autowired
	private FileService filservice;
	
	@Value("${project.image}")
	private String path;
	
	//create post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postdto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		
		PostDto createPost=this.postservice.createpost(postdto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
	//get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		List<PostDto> posts=this.postservice.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	//get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)
	{
		List<PostDto> posts=this.postservice.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
    
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getallPosts(@RequestParam(value = "pagenumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pagenumber
			,@RequestParam(value = "pagenumber",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pagesize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "SortDir",defaultValue = AppConstants.SORT_DIR  ,required = false)String sortDir)
	
	{
		PostResponse  postresponse=this.postservice.getAllpost(pagenumber,pagesize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postresponse,HttpStatus.OK);
		
	}
	
	//get post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getpostById( @PathVariable Integer postId)
	{
		PostDto  postdto=this.postservice.getPostById(postId);
		return new ResponseEntity<PostDto>(postdto,HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId)
	{
		this.postservice.deletePost(postId);
		return new ApiResponse("Post deleted succefully!!",true);
	
	}
	//update post
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,  @PathVariable Integer postId)
	{
		
	PostDto updatepost=	 this.postservice.updatePost(postdto, postId);
		return new ResponseEntity<PostDto>(updatepost,HttpStatus.OK);
	
	}
	
	//searchpost
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostById(@PathVariable ("keywords") String keywords)
	{
		List<PostDto> posts=this.postservice.searchPosts(keywords);
		return  new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	//uploadImage
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto>uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto postdto=this.postservice.getPostById(postId);
		String filename=this.filservice.uplodImage(path, image);
		
		postdto.setImageName(filename);
		this.postservice.updatePost(postdto, postId);
		return new ResponseEntity<PostDto>(postdto,HttpStatus.OK);
	}
}
