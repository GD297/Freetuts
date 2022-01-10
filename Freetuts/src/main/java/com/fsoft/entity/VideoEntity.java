package com.fsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "video")
public class VideoEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "videoID", columnDefinition = "uniqueidentifier")
	private String videoID;
	
	@Column(name = "linkVideo")
	private String linkVideo;
	
	@Column(name = "blogID", columnDefinition = "uniqueidentifier")
	private String blogID;

	public VideoEntity(String videoID, String linkVideo, String blogID) {
		super();
		this.videoID = videoID;
		this.linkVideo = linkVideo;
		this.blogID = blogID;
	}
	
	@ManyToOne
	@JoinColumn(name = "blogID", insertable = false, updatable = false)
	@JsonIgnore
	private BlogEntity blog_video;
}
