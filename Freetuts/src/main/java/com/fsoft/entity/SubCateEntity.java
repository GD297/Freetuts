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
@Table(name = "subCategory")
public class SubCateEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "subCateID", columnDefinition = "uniqueidentifier")
	private String subCateID;
	
	@Column(name = "title", columnDefinition = "nvarchar(255)")
	private String title;
	
	@Column(name = "linkSubCate", columnDefinition = "varchar(255)")
	private String linkSubCate;
	
	@Column(name = "author", columnDefinition = "nvarchar(MAX)")
	private String author;
	
	@Column(name = "createDate", columnDefinition = "datetime")
	private Timestamp createDate;
	
	@Column(name = "cateID", columnDefinition = "uniqueidentifier")
	private String cateID;	

	@Column(name = "content", columnDefinition = "nvarchar(max)")
	private String content;	

	public SubCateEntity(String subCateID, String title, String linkSubCate, String author, Timestamp createDate,
			String cateID) {
		super();
		this.subCateID = subCateID;
		this.title = title;
		this.linkSubCate = linkSubCate;
		this.author = author;
		this.createDate = createDate;
		this.cateID = cateID;
	}

	@OneToMany(mappedBy = "subcate", fetch = FetchType.LAZY)
	private List<BlogEntity> blogs;
	
	@ManyToOne
	@JoinColumn(name = "cateID", insertable = false, updatable = false)
	@JsonIgnore
	private CateEntity cate;
}

