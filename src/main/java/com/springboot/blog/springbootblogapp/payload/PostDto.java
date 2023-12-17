package com.springboot.blog.springbootblogapp.payload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {

	private long id;
	
	@NotEmpty
	@Size(min=2,message="title should be min size 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min=10,message="description at least should have 10 chars")
	private String description;
	
	@NotEmpty
	private String content;
	private Set<CommentDto>comments;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	
	
	


	
}
