package com.springboot.blog.springbootblogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.springbootblogapp.payload.CommentDto;
import com.springboot.blog.springbootblogapp.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId")long postId,@Valid @RequestBody CommentDto commentDto){
		return new ResponseEntity<>(commentService.createComment(postId, commentDto),HttpStatus.CREATED);
				
	}
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value="postId") Long postId) {
		return commentService.getCommentByPostId(postId);
	}
	
	@GetMapping("/posts/{postId}/comment/{Id}")
	public ResponseEntity<CommentDto> getCommentByPost(@PathVariable(value="postId") Long postId,@PathVariable(value="Id") Long commentId) {
		
		return new ResponseEntity<>(commentService.getCommentById(postId, commentId),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/comment/{Id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") Long postId,@PathVariable(value="Id") Long commentId,@Valid @RequestBody CommentDto commentRequest) {
		
		return new ResponseEntity<>(commentService.updateComment(postId, commentId,commentRequest),HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}/comment/{Id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value="postId") Long postId,@PathVariable(value="Id") Long commentId) {
		commentService.deleteCommentByPostId(postId,commentId);
		return new ResponseEntity<>("delete comment successfully!",HttpStatus.OK);
	}
	
}
