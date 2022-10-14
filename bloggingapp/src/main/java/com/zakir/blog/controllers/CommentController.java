package com.zakir.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zakir.blog.entities.Comment;
import com.zakir.blog.paylods.ApiResponse;
import com.zakir.blog.paylods.CommentDto;
import com.zakir.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentservice;
	
	@PostMapping("/post/{postId}/comments")
	private  ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentdto,@PathVariable Integer postId)
	{
		CommentDto comment=this.commentservice.createComment(commentdto, postId);
		return new ResponseEntity<CommentDto>(comment,HttpStatus.OK);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
		this.commentservice.deleteComment(commentId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfull",true),HttpStatus.OK);
		
	}

}
