package com.fsoft.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTypeDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String typeID;
	private String typeName;
	private String link;
}
