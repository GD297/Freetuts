package com.fsoft.dto;

import java.io.Serializable;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	public String image;
	private String bio;
	private String education;
	private String experience;
	private String facebook;
	private String github;
	private String instagram;
	private String firstname;
	private String lastname;
	private String mail;
	private String location;
	private Long phone;
	private Date birthday;
	private String imageProfile;
	private String introduce;
    private boolean accountNonLocked;
    private int failedAttempt;
    private Date lockTime;
}
