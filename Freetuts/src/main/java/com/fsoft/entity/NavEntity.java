package com.fsoft.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nav")
public class NavEntity {
	
	@Id
	@Column(name = "navID",columnDefinition = "varchar(5)")
	private String navID;
	
	@Column(name = "title", columnDefinition = "nvarchar(255)")
	private String title;
	
	@Column(name = "linkContent", columnDefinition = "nvarchar(255)")
	private String linkContent;
	
	@Column(name = "author", columnDefinition = "nvarchar(50)")
	private String author;
	
	@Column(name = "createDate", columnDefinition = "datetime")
	private Timestamp createDate;
	
	@Column(name = "content", columnDefinition = "nvarchar(MAX)")
	private String content;
	
	public NavEntity(String navID, String title, String linkContent, String author, Timestamp createDate,
			String content) {
		super();
		this.navID = navID;
		this.title = title;
		this.linkContent = linkContent;
		this.author = author;
		this.createDate = createDate;
		this.content = content;
	}

	@OneToMany(mappedBy = "nav", fetch = FetchType.LAZY)
	private List<CateEntity> cates;
}
