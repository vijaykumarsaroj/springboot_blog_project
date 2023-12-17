package com.springboot.blog.springbootblogapp.controller;

import java.util.List;

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

import com.springboot.blog.springbootblogapp.payload.PostDto;
import com.springboot.blog.springbootblogapp.payload.PostResponse;
import com.springboot.blog.springbootblogapp.service.PostService;
import com.springboot.blog.springbootblogapp.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	//postMapping 
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED); 
	}
	@GetMapping
	public PostResponse getAllPosts(
			    @RequestParam(value="pageNo",defaultValue=AppConstants.DEFAULT_PAGE_NUMBER,required=false) int pageNo,
			    @RequestParam(value="pageSize",defaultValue=AppConstants.DEFAULT_PAGE_SIZE,required=false) int pageSize,
			    @RequestParam(value="sortBy",defaultValue=AppConstants.DEFAULT_SORT_BY,required=false) String sortBy,
			    @RequestParam(value="sortDir",defaultValue=AppConstants.DEFAULT_SORT_DIRECTION,required=false) String sortDir
			){
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
		
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable("id") long id){
		
		PostDto postResponse = postService.updatePost(postDto, id);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePost(@PathVariable("id") long id) {
		
		postService.deletePostById(id);
		return new ResponseEntity<>("post entity delete successfully",HttpStatus.OK);
	}
}
