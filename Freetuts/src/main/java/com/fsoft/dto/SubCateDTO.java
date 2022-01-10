
package com.fsoft.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.fsoft.entity.BlogEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCateDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String subCateID;
	private String title;
	private String linkSubCate;
	private String author;
	private Timestamp createDate;
	private String cateID;
	private String content;
	
	private CateDTO cate;
	private List<BlogEntity> blogs;
}

