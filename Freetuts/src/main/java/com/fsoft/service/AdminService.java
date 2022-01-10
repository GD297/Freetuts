package com.fsoft.service;

import java.util.List;

import com.fsoft.dto.AdminDTO;

public interface AdminService {
	
	void login (String username, String password);
	String findLoggedInUsername();
	List<AdminDTO> findAllUser();
	AdminDTO findByUsername(String username);
	void increaseFailedAttempts(AdminDTO adminDTO);
	void resetFailedAttempts(String username);
	void lock(AdminDTO adminDTO);
	boolean unlockWhenTimeExpired(AdminDTO adminDTO);
}
