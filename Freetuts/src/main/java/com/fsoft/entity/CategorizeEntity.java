package com.fsoft.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categorize")
public class CategorizeEntity {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "categorizeid", columnDefinition = "uniqueidentifier")
	private String categorizeid;
	
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
	
	@OneToMany(mappedBy = "categorize", fetch = FetchType.LAZY)
	private List<CateEntity> cates;
}
