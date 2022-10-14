package com.zakir.blog.services;

import com.zakir.blog.paylods.CommentDto;

public interface CommentService {
	
	
	CommentDto createComment(CommentDto commentdto,Integer postId);
	
	void deleteComment(Integer commentId);

}
