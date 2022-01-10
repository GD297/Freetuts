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
@Table(name = "image")
public class ImageEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "imageID", columnDefinition = "uniqueidentifier")
	private String imageID;
	
	@Column(name = "linkImage")
	private String linkImage;
	
	@Column(name = "blogID", columnDefinition = "uniqueidentifier")
	private String blogID;
	
	@Column(name = "alt")
	private String alt;
	
	@ManyToOne
	@JoinColumn(name = "blogID", insertable = false, updatable = false)
	@JsonIgnore
	private BlogEntity blog_image;
	
	public ImageEntity(String imageID, String linkImage, String blogID, String alt) {
		super();
		this.imageID = imageID;
		this.linkImage = linkImage;
		this.blogID = blogID;
		this.alt = alt;
	}
}
