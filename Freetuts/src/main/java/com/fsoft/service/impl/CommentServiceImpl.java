package com.fsoft.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.dto.CommentDTO;
import com.fsoft.entity.CommentEntity;
import com.fsoft.repository.CommentRepository;
import com.fsoft.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<CommentDTO> getCommentByBlog(String blogID) {		
		List<CommentEntity> listCommentEntity = commentRepository.findByBlogID(blogID);
		List<CommentDTO> listComment = modelMapper.map(listCommentEntity, new TypeToken<List<CommentDTO>>() {}.getType());
		return listComment;
	}

	@Override
	public void addComment(CommentDTO dto, String blogID) {
		dto.setCommentID(UUID.randomUUID().toString());
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		dto.setTime(time);
		dto.setBlogID(blogID);
		System.out.println("DTO: "+dto.toString());
		CommentEntity entity = modelMapper.map(dto, CommentEntity.class);
		System.out.println("AAAAAAAAAAAAAAAAAAAAA" + dto.toString());
		commentRepository.save(entity);		
	}
	
}
