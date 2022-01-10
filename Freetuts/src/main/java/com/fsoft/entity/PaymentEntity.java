package com.fsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "payment")
public class PaymentEntity {
	
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@Column(name = "paymentID", columnDefinition = "uniqueidentifier")
	private String paymentID;
	
	@Column(name = "name", columnDefinition = "nvarchar(255)")
	private String name;
	
	@Column(name = "phone", columnDefinition = "varchar(50)")
	private String phone;
	
	@Column(name = "email", columnDefinition = "varchar(255)")
	private String email;

	@Column(name = "facebook", columnDefinition = "varchar(255)")
	private String facebook;
	
	@Column(name = "paymentMethod")
	private String paymentMethod;

	public PaymentEntity(String paymentID, String name, String phone, String email, String facebook,
			String paymentMethod) {
		super();
		this.paymentID = paymentID;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.facebook = facebook;
		this.paymentMethod = paymentMethod;
	}
}
