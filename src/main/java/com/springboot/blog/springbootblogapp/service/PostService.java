package com.springboot.blog.springbootblogapp.service;

import java.util.List;

import com.springboot.blog.springbootblogapp.payload.PostDto;
import com.springboot.blog.springbootblogapp.payload.PostResponse;

public interface PostService {
 
	PostDto createPost(PostDto postDto);
	
	PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto,long id);
	
	void deletePostById(long id);
}
