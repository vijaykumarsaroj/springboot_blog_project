package com.springboot.blog.springbootblogapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogapp.entity.Comment;
import com.springboot.blog.springbootblogapp.entity.Post;
import com.springboot.blog.springbootblogapp.exception.BlogAPIException;
import com.springboot.blog.springbootblogapp.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogapp.payload.CommentDto;
import com.springboot.blog.springbootblogapp.payload.CommentResponse;
import com.springboot.blog.springbootblogapp.repository.CommentRepository;
import com.springboot.blog.springbootblogapp.repository.PostRepository;
import com.springboot.blog.springbootblogapp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;
	public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,ModelMapper mapper) {
		this.commentRepository = commentRepository;
		this.postRepository= postRepository;
		this.mapper = mapper;
	}

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
		Comment comment = mapToEntity(commentDto);
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
		
		//set comment to entity
		comment.setPost(post);
		
		//comment saave to db
		Comment newComment = commentRepository.save(comment);

		return mapToDTO(newComment);
	}

	private CommentDto mapToDTO(Comment comment) {
		
		CommentDto commentDto = mapper.map(comment, CommentDto.class);
//		CommentDto commentDto = new CommentDto();
//		commentDto.setId(comment.getId());
//		commentDto.setName(comment.getName());
//		commentDto.setEmail(comment.getEmail());
//		commentDto.setBody(comment.getBody());
		
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		
		Comment comment = mapper.map(commentDto, Comment.class);
//		Comment comment = new Comment();
//		comment.setId(commentDto.getId());
//		comment.setName(commentDto.getName());
//		comment.setEmail(commentDto.getEmail());
//		comment.setBody(commentDto.getBody());
		return comment;
	}

	@Override
	public List<CommentDto>getCommentByPostId(Long postId) {
		// retrieve comment by post id
		List<Comment>comments=commentRepository.findByPostId(postId);
		return comments.stream().map(comment->mapToDTO(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {
		// retrieve post from db
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
		// retrieve comment from db
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not exist");
		}
		return mapToDTO(comment);
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
		
		// retrieve post from db
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
				// retrieve comment from db
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
		if(!comment.getPost().getId().equals(post.getId())) {
					throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not exist");
		}
		comment.setName(commentRequest.getName());
		comment.setEmail(commentRequest.getEmail());
		comment.setBody(commentRequest.getBody());
		
		Comment updateComment= commentRepository.save(comment);
		return mapToDTO(updateComment);
	}

	@Override
	public void deleteCommentByPostId(Long postId,Long commentId) {
		// retrieve post from db
				Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","id",postId));
						// retrieve comment from db
				Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
				if(!comment.getPost().getId().equals(post.getId())) {
							throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not exist");
				}
		commentRepository.delete(comment);
	}
}
