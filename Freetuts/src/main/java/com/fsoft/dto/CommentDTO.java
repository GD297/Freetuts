package com.fsoft.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String commentID;
	private String name;
	private String email;
	private String content;
	private Timestamp time;
	private String blogID;
}
