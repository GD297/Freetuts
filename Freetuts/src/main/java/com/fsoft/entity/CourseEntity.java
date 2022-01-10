package com.fsoft.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "course")
public class CourseEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "courseID", columnDefinition = "uniqueidentifier")
	private String courseID;
	
	@Column(name = "courseName")
	private String courseName;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "typeID" , columnDefinition = "uniqueidentifier")
	private String typeID;
	
	@Column(name = "blogID")
	private String blogID;
	
    @Column(name = "image")
	private String image;

	public CourseEntity(String courseID, String courseName, double price, String blogID, String image) {
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.price = price;
		this.blogID = blogID;
		this.image = image;
	}
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
	public BlogEntity blog;
	
	@ManyToOne
	@JoinColumn(name = "typeID", insertable = false, updatable = false)
	@JsonIgnore
	public CourseTypeEntity type;
}
