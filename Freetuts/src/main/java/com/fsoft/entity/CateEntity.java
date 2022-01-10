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
@Table(name = "category")
public class CateEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "cateID", columnDefinition = "uniqueidentifier")
	private String cateID;
	
	@Column(name = "title", columnDefinition = "nvarchar(255)")
	private String title;
	
	@Column(name = "linkCate", columnDefinition = "varchar(255)")
	private String linkCate;
	
	@Column(name = "navID", columnDefinition = "varchar(5)")
	private String navID;
	
	@Column(name = "categorizeid", columnDefinition = "uniqueidentifier")
	private String categorizeid;
	
	@Column(name = "content", columnDefinition = "ntext")
	private String content;

	@Column(name = "createDate", columnDefinition = "datetime")
	private Timestamp createDate;
	
	@Column(name = "author", columnDefinition = "nvarchar(250)")
	private String author;

	public CateEntity(String cateID, String title, String linkCate, String navID, String categorizeid, String content,
			Timestamp createDate, String author) {
		super();
		this.cateID = cateID;
		this.title = title;
		this.linkCate = linkCate;
		this.navID = navID;
		this.categorizeid = categorizeid;
		this.content = content;
		this.createDate = createDate;
		this.author = author;
	}

	@OneToMany(mappedBy = "cate", fetch = FetchType.LAZY)
	private List<SubCateEntity> subcates;
	
	@ManyToOne
	@JoinColumn(name = "navID", insertable = false, updatable = false)
	@JsonIgnore
	private NavEntity nav;
	
	@ManyToOne
	@JoinColumn(name = "categorizeid", insertable = false, updatable = false)
	@JsonIgnore
	private CategorizeEntity categorize;
}
