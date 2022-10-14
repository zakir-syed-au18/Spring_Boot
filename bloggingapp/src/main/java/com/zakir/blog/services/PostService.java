package com.zakir.blog.services;

import java.util.List;

import com.zakir.blog.entities.Post;
import com.zakir.blog.paylods.PostDto;
import com.zakir.blog.paylods.PostResponse;

public interface PostService {
	
	
	
	PostDto  createpost(PostDto  postdto,Integer  userId,Integer  categoryId);
	
	PostDto updatePost(PostDto postdto,Integer postId);
	
	
	void deletePost(Integer postId);
	
	PostResponse getAllpost(Integer pagenumber,Integer pagesize,String sortBy,String sortDir);
	
	PostDto  getPostById(Integer postId);
	
	List<PostDto>getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);

	

}
