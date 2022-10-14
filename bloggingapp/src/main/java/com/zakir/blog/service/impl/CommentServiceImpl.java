package com.zakir.blog.service.impl;

import javax.persistence.ManyToOne;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zakir.blog.entities.Comment;
import com.zakir.blog.entities.Post;
import com.zakir.blog.execptions.ResourceNotFoundException;
import com.zakir.blog.paylods.CommentDto;
import com.zakir.blog.repositories.CommentRepo;
import com.zakir.blog.repositories.PostRepo;
import com.zakir.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private CommentRepo commentrepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CommentDto createComment(CommentDto commentdto, Integer postId) {
		Post post=this.postrepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
		
		Comment comment=this.modelmapper.map(commentdto, Comment.class);
		
		comment.setPost(post);
		
		Comment savedcomment=this.commentrepo.save(comment);
		
		return this.modelmapper.map(savedcomment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment com=this.commentrepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "id", commentId));
		
		this.commentrepo.delete(com);
	}

}
