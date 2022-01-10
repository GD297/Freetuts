package com.fsoft.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NavDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String navID;
	private String title;
	private String linkContent;
	private String author;
	private Timestamp createDate;
	private String content;
}
