package com.fsoft.entity;

import java.util.Date;
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
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Admin")
public class AdminEntity  {


//	public static final String CHECK_STATUS = "^(ACTIVE||DEACTIVE)$";
	

	@Id
	@Column(name = "username")
//	@Email(message = "wrong format !!!")
	private String username;
	
	@Column(name = "password")
//	@Size(min = 8, max = 32, message = "the password must be 8 - 32 characters")
	private String password;
	
//	@Column(name = "status")
////	@Pattern(regexp = CHECK_STATUS)
//	private String status;
	
	@Column(name = "image")
	private String image;

	@Column(name = "bio", columnDefinition = "text")
	private String bio;
	
	@Column(name = "education", columnDefinition = "text")
	private String education;
	
	@Column(name = "experience", columnDefinition = "text")
	private String experience;
	
	@Column(name = "facebook")
	private String facebook;
	
	@Column(name = "github")
	private String github;
	
	@Column(name = "instagram")
	private String instagram;
	
	@Column(name = "firstName")
	private String firstname;
	
	@Column(name = "lastName")
	private String lastname;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "phone")
	private Long phone;
	
	@Column(name = "birthday", columnDefinition = "date")
	private Date birthday;
	
	@Column(name = "imageProfile")
	private String imageProfile;
	
	@Column(name = "introduce")
	private String introduce;
	
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
     
    @Column(name = "failed_attempt")
    private int failedAttempt;
     
    @Column(name = "lock_time")
    private Date lockTime;
	
	@OneToMany(mappedBy = "admin",fetch = FetchType.LAZY)
	private List<SkillsAdmin>  skillsAdmin;
	
	
	
//	@ManyToMany(mappedBy = "admin", fetch = FetchType.LAZY)
//	private List<SkillsEntity> skill;
	
}
