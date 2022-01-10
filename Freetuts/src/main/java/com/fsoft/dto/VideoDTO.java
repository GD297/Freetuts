package com.fsoft.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String videoID;
	private String linkVideo;
	private String blogID;
}
