package com.springboot.blog.springbootblogapp.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

	private long id;
	
	@NotEmpty(message="name should not be null or empty")
	private String name;
	
	@NotEmpty(message="email should not be null or empty")
	@Email
	private String email;
	
	@NotEmpty(message="body should not be null or emty")
	@Size(min=10,message="body should not be at least 10 char")
	private String body;
	private String post;
	public CommentDto() {
		
	}
	public CommentDto(long id, String name, String email, String body) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
}
