package com.springboot.blog.springbootblogapp.payload;

import java.util.List;

public class CommentResponse {

	private List<CommentDto>data;
	public CommentResponse() {
		
	}
	public CommentResponse(List<CommentDto> data) {
		this.data = data;
	}

	public List<CommentDto> getData() {
		return data;
	}

	public void setData(List<CommentDto> data) {
		this.data = data;
	}
	
}
