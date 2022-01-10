package com.fsoft.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.dto.SkillsAdminDTO;
import com.fsoft.entity.SkillsAdmin;
import com.fsoft.repository.SkillsAdminRepository;
import com.fsoft.service.SkillsAdminService;

@Service
public class SkillsAdminServiceImpl implements SkillsAdminService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SkillsAdminRepository skillsAdminRepository;
	
	@Override
	public List<SkillsAdminDTO> getAllSkillsAdmin(String username) {
		// TODO Auto-generated method stub
		List<SkillsAdmin> listSkillsAdmin = skillsAdminRepository.findAllByAdminUsername(username);
		List<SkillsAdminDTO> listSkillsAdminDto = modelMapper.map(listSkillsAdmin,  new TypeToken<List<SkillsAdmin>>() {}.getType());
		return listSkillsAdminDto;
	}

}
