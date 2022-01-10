package com.fsoft.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type")
public class CourseTypeEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "typeID", columnDefinition = "uniqueidentifier")
	private String typeID;
	
	@Column(name = "typeName")
	private String typeName;
	
	@Column(name = "link")
	private String link;
	
	public CourseTypeEntity(String typeID, String typeName, String link) {
		super();
		this.typeID = typeID;
		this.typeName = typeName;
		this.link = link;
	}
	
	@OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
	public List<CourseEntity> coursee;
}
