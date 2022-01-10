package com.fsoft.service;

import java.util.List;

import com.fsoft.dto.CommentDTO;

public interface CommentService {

	public List<CommentDTO> getCommentByBlog(String blogID);
	
	void addComment(CommentDTO dto, String blogID);
}
