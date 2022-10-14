package com.zakir.blog.service.impl;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.zakir.blog.entities.Category;
import com.zakir.blog.entities.Post;
import com.zakir.blog.entities.User;
import com.zakir.blog.execptions.ResourceNotFoundException;
import com.zakir.blog.paylods.PostDto;
import com.zakir.blog.paylods.PostResponse;
import com.zakir.blog.repositories.CategoryRepo;
import com.zakir.blog.repositories.PostRepo;
import com.zakir.blog.repositories.UserRepo;
import com.zakir.blog.services.PostService;




@Service
public class PostServiceImpl  implements PostService {

	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private  ModelMapper modelmapper;
	
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private CategoryRepo  categoryrepo;

	@Override
	public PostDto createpost(PostDto postdto, Integer userId, Integer categoryId) {
	
		User user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		Category category=this.categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		Post post=this.modelmapper.map(postdto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate( new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postrepo.save(post);
		return this.modelmapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postId) {
		Post post=	this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "Id", postId));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(post.getImageName());
		Post updatePost=this.postrepo.save(post);
		return this.modelmapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
	Post post=	this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "Id", postId));
		this.postrepo.delete(post);
	}

	@Override
	public PostResponse  getAllpost(Integer pagenumber,Integer pagesize,String sortBy,String sortDir) {
		
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
	
		Pageable p=PageRequest.of(pagenumber, pagesize,Sort.by(sortBy).descending());
		
		Page<Post> pageposts=this.postrepo.findAll(p);
		List<Post> allposts=pageposts.getContent();
		
		List<PostDto> postdtos=allposts.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postresponse=new PostResponse();
		
		postresponse.setContent(postdtos);
		postresponse.setPagenumber(pageposts.getNumber());
		postresponse.setPagesize(pageposts.getSize());
		postresponse.setTotalelements(pageposts.getTotalElements());
		postresponse.setTotalpages(pageposts.getTotalPages());
		postresponse.setLastpage(pageposts.isLast());
		
		
		return postresponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
	
		Post post=this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "Id", postId));
		return this.modelmapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=this.categoryrepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<Post> posts=this.postrepo.findByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user=this.userrepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "Id", userId));
		List<Post> posts=this.postrepo.findByUser(user);
		List<PostDto> postdtos=posts.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postdtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts=this.postrepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postdtos=posts.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}
	
	


}
