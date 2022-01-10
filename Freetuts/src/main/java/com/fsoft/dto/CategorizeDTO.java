package com.fsoft.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategorizeDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categorizeid;
	private String title;
	private String linkType;
	private String author;
	private Timestamp createDate;
	private String content;

	
	List<CateDTO> cates;
}
