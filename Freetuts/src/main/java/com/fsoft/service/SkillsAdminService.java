package com.fsoft.service;

import java.util.List;

import com.fsoft.dto.SkillsAdminDTO;

public interface SkillsAdminService {
	List<SkillsAdminDTO> getAllSkillsAdmin(String username);
}
