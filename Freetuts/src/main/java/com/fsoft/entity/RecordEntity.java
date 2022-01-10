package com.fsoft.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "record_update")
public class RecordEntity {

	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "notiID", columnDefinition = "uniqueidentifier")
	private String notiID;
	
	@Column(name = "message", columnDefinition = "nvarchar(255)")
	private String message;
	
	@Column(name = "time", columnDefinition = "datetime")
	private Timestamp time;
	
	@Column(name = "author", columnDefinition = "nvarchar(255)")
	public String author;
	
	@Column(name = "blogID", columnDefinition = "uniqueidentifier")
	private String blogID;
	
	@Column(name = "status", columnDefinition = "nvarchar(250)")
	private String status;
	
	
}
