 package com.fsoft.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fsoft.entity.AdminEntity;

public class UserDetailsService implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AdminEntity adminEntity;

	//@Autowired
	public UserDetailsService(AdminEntity adminEntity) {
		this.adminEntity = adminEntity;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		for (Role role : user.getRoles()) {
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		}

		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return adminEntity.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return adminEntity.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return adminEntity.isAccountNonLocked();

	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getImage() {
		return adminEntity.getImage();
	}
	
	public String getRole() {
		return "Administrator";
	}
	
	public String getImageProfile() {
		return adminEntity.getImageProfile();
	}

	public String getFullName() {
		return adminEntity.getFirstname() + " " + adminEntity.getLastname();
	}
	
	public String getLastName() {
		return adminEntity.getLastname();
	}
	
	public String getFirstName() {
		return adminEntity.getFirstname();
	}
	
	public Long getPhone() {
		return adminEntity.getPhone();
	}
	
	public Date getBirthday() {
		return adminEntity.getBirthday();
	}
	
	public String getMail() {
		return adminEntity.getMail();
	}
	
	public String getFaceBook() {
		return adminEntity.getFacebook();
	}
	
	public String getInstagram() {
		return adminEntity.getInstagram();
	}
	
	public String getGithub() {
		return adminEntity.getGithub();
	}
	
	public String getLocation() {
		return adminEntity.getLocation();
	}
	
	public String getEducation() {
		return adminEntity.getEducation();
	}
	
	public String getExperience() {
		return adminEntity.getExperience();
	}
	
	public String getIntroduce() {
		return adminEntity.getIntroduce();
	}
	
	public String getBio() {
		return adminEntity.getBio();
	}
	
	public AdminEntity getAdmin() {
		return this.adminEntity;
	}
	
}
