package com.fsoft.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "blog")
public class BlogEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "blogID", columnDefinition = "uniqueidentifier")
	private String blogID;

	@Column(name = "title", columnDefinition = "nvarchar(MAX)")
	private String title;

	@Column(name = "author", columnDefinition = "nvarchar(MAX)")
	private String author;

	@Column(name = "content", columnDefinition = "nvarchar(MAX)")
	private String content;

	@Column(name = "linkImage", columnDefinition = "varchar(MAX)")
	private String linkImage;

	@Column(name = "linkContent", columnDefinition = "varchar(MAX)")
	private String linkContent;
	
	@Column(name = "subCateID", columnDefinition = "uniqueidentifier")
	private String subCateID;

	@Column(name = "createDate", columnDefinition = "datetime")
	private Timestamp createDate;
	
	@Column(name = "type", columnDefinition = "varchar(50)")
	public String type;

	@Column(name = "status", columnDefinition = "varchar(255)")
	private String status;
	
	public BlogEntity(String blogID, String title, String author, String content, String linkImage, String linkContent,
			String subCateID, Timestamp createDate, String type) {
		super();
		this.blogID = blogID;
		this.title = title;
		this.author = author;
		this.content = content;
		this.linkImage = linkImage;
		this.subCateID = subCateID;
		this.linkContent = linkContent;
		this.createDate = createDate;
		this.type = type;
	}

	@OneToMany(mappedBy = "blog", fetch = FetchType.LAZY)
	private List<CommentEntity> comments;

	@ManyToOne
	@JoinColumn(name = "subCateID", insertable = false, updatable = false)
	@JsonIgnore
	private SubCateEntity subcate;

	@OneToMany(mappedBy = "blog_image", fetch = FetchType.LAZY)
	private List<ImageEntity> image;

	@OneToMany(mappedBy = "blog_video", fetch = FetchType.LAZY)
	private List<VideoEntity> video;

	@OneToOne(mappedBy = "blog")
	private CourseEntity course;
	
}
