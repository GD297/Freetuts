package com.fsoft.entity;

import java.sql.Timestamp;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class CommentEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "commentID", columnDefinition = "uniqueidentifier")
	private String commentID;
	
	@Column(name = "name", columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name = "email", columnDefinition = "varchar(255)")
	private String email;
	
	@Column(name = "content", columnDefinition = "nvarchar(MAX)")
	private String content;
	
	@Column(name = "time", columnDefinition = "datetime")
	private Timestamp time;
	
	@Column(name = "blogID", columnDefinition = "uniqueidentifier")
	private String blogID;
	
	public CommentEntity(String commentID, String name, String email, String content, Timestamp time, String blogID) {
		super();
		this.commentID = commentID;
		this.name = name;
		this.email = email;
		this.content = content;
		this.time = time;
		this.blogID = blogID;
	}



	@ManyToOne
	@JoinColumn(name = "blogID", insertable = false, updatable = false)
	@JsonIgnore
	private BlogEntity blog;
}
