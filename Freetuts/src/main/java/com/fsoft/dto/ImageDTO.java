package com.fsoft.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String imageID;
	private String linkImage;
	private String blogID;
	private String alt;
}
