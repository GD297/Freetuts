package com.fsoft.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fsoft.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String blogID;
	private String title;
	private String author;
	private String content;
	private String linkImage;
	private String linkContent;
	private String subCateID;
	private Timestamp createDate;
	private String type;
	private String status;
	
	private SubCateDTO subcate;
	private List<CommentEntity> comments;

}
