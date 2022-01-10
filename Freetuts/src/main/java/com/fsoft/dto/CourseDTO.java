package com.fsoft.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String courseID;
	private String courseName;
	private double price;
	private String typeID;
	private String blogID;
	private String image;
}
