package com.springboot.blog.springbootblogapp.service;

import java.util.List;

import com.springboot.blog.springbootblogapp.payload.CommentDto;
import com.springboot.blog.springbootblogapp.payload.CommentResponse;

public interface CommentService {

	CommentDto createComment(long postId,CommentDto commentDto);
	
	List<CommentDto>getCommentByPostId(Long postId);
	
	CommentDto getCommentById(Long postId,Long commentId);
	CommentDto updateComment(Long postId,Long commentId,CommentDto commentRequest);
	
	void deleteCommentByPostId(Long postId,Long commentId);
}
