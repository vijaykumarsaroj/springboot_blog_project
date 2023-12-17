//package com.springboot.blog.springbootblogapp.service.impl;
//
//import java.awt.print.Pageable;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import org.springframework.stereotype.Service;
//
//import com.springboot.blog.springbootblogapp.entity.Post;
//import com.springboot.blog.springbootblogapp.exception.ResourceNotFoundException;
//import com.springboot.blog.springbootblogapp.payload.PostDto;
//import com.springboot.blog.springbootblogapp.repository.PostRepository;
//import com.springboot.blog.springbootblogapp.service.PostService;
package com.springboot.blog.springbootblogapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable; // Keep only this import
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogapp.entity.Post;
import com.springboot.blog.springbootblogapp.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogapp.payload.PostDto;
import com.springboot.blog.springbootblogapp.payload.PostResponse;
import com.springboot.blog.springbootblogapp.repository.PostRepository;
import com.springboot.blog.springbootblogapp.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	private PostRepository postRepository;
	private ModelMapper mapper;
	
	public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
		this.postRepository = postRepository;
		this.mapper = mapper;
	}


	@Override
	public PostDto createPost(PostDto postDto) {
		//convert Dto to entity
		Post post = mapToEntity(postDto);
		
		Post newPost = postRepository.save(post);
		
		// convert enitity to postDto
		PostDto postResponse = mapToDTO(newPost);
		
		
		return postResponse;
	}


	@Override
	public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
			Sort.by(sortBy).descending();
		//create pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> posts= postRepository.findAll(pageable);
		//get content for page object
		List<Post> listOfPosts = posts.getContent();
		List<PostDto>content=listOfPosts.stream().map(post->mapToDTO(post)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
		
	}
	//convert entity to DTO
	private PostDto mapToDTO(Post post) {
		
		PostDto postDto = mapper.map(post, PostDto.class);
//		PostDto postDto = new PostDto();
//		
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setDescription(post.getDescription());
//		postDto.setContent(post.getContent());
		return postDto;
	}
	
	//convert DTO to entity
	private Post mapToEntity(PostDto postDto) {
		
		Post post = mapper.map(postDto, Post.class);
//		Post post = new Post();
//		post.setId(postDto.getId());
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		
		return post;
	}


	@Override
	public PostDto getPostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		return mapToDTO(post);
	}


	@Override
	public PostDto updatePost(PostDto postDto,long id) {
		// get id from database
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
	    
		Post updatePost = postRepository.save(post);
		return mapToDTO(updatePost);
	}


	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		
		postRepository.delete(post);
	}
	
}
