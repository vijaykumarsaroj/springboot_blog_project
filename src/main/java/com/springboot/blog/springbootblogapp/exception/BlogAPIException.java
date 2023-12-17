package com.springboot.blog.springbootblogapp.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;
	
	public BlogAPIException() {
		
	}
	public BlogAPIException(HttpStatus status, String message) {
		
		this.status = status;
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
