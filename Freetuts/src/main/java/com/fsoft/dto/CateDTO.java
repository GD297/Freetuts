package com.fsoft.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fsoft.entity.SubCateEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CateDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String cateID;
	private String title;
	private String linkCate;
	private String navID;

	private String categorizeid;
	private Timestamp createDate;
	private String content;
	private String author;

	private CategorizeDTO categorize;
	private NavDTO nav;	
	private List<SubCateDTO> subcates;

}
