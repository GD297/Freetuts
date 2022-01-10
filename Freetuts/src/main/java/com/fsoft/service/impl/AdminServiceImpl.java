package com.fsoft.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsoft.dto.AdminDTO;
import com.fsoft.entity.AdminEntity;
import com.fsoft.repository.AdminRepository;
import com.fsoft.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	public static final int MAX_FAILED_ATTEMPTS = 3;

	private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AdminRepository adminRepository;
	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public String findLoggedInUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}

		return null;
	}

	@Override
	public void login(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug(String.format("Auto login %s successfully!", username));
		}

	}
	
	@Override
	public AdminDTO findByUsername(String username) {
		// TODO Auto-generated method stub
		AdminDTO adminDTO = null;
		try {
		AdminEntity adminEntity = adminRepository.findByUsername(username);
		 adminDTO = modelMapper.map(adminEntity, AdminDTO.class);
		}catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		return adminDTO;
	}

	@Override
	public List<AdminDTO> findAllUser() {
		List<AdminEntity> listAdminEntities = adminRepository.findAll();
		List<AdminDTO> lisAdminDTOs = modelMapper.map(listAdminEntities, new TypeToken<List<AdminEntity>>() {
		}.getType());
		return lisAdminDTOs;
	}

	@Override
	@Transactional
	public void increaseFailedAttempts(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = modelMapper.map(adminDTO, AdminEntity.class);
		int newFailAttempts = adminDTO.getFailedAttempt() + 1;
		adminRepository.setFailedAttemptsByUsername(newFailAttempts, adminEntity.getUsername());
	}

	@Override
	@Transactional
	public void resetFailedAttempts(String username) {
		// TODO Auto-generated method stub
		adminRepository.setFailedAttemptsByUsername(0, username);
	}

	@Override
	@Transactional
	public void lock(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		AdminEntity adminEntity = modelMapper.map(adminDTO, AdminEntity.class);
		adminEntity.setAccountNonLocked(false);
		adminEntity.setLockTime(new Date());

		adminRepository.save(adminEntity);
	}

	@Override
	@Transactional
	public boolean unlockWhenTimeExpired(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		
		AdminEntity adminEntity = modelMapper.map(adminDTO, AdminEntity.class);
		
		long lockTimeInMillis = adminDTO.getLockTime().getTime();
		long currentTimeInMillis = System.currentTimeMillis();
		
		if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
			adminEntity.setAccountNonLocked(true);
			adminEntity.setLockTime(null);
			adminEntity.setFailedAttempt(0);
             
            adminRepository.save(adminEntity);
             
            return true;
        }
		
		return false;
	}



}
