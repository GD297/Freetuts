package com.fsoft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.entity.AdminEntity;
import com.fsoft.repository.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
//	@Autowired
//	private AdminDTO adminEntity ;
//	
//	 public UserDetailsServiceImpl(AdminDTO adminEntity) {
//		// TODO Auto-generated constructor stub
//		 this.adminEntity = adminEntity;
//	}
	
	@Autowired
	public AdminRepository adminRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminEntity adminEntity = adminRepository.findByUsername(username);
		if (adminEntity == null) {
			throw new UsernameNotFoundException(username);
		}
			

//		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
////		for (Role role : user.getRoles()) {
//			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
////		}
//
//		return new org.springframework.security.core.userdetails.User(adminEntity.getUsername(), adminEntity.getPassword(),grantedAuthorities);
		return new com.fsoft.service.impl.UserDetailsService(adminEntity);
	}
//	
//	public String getImage() {
//		return adminEntity.getImage();
//	}

}
