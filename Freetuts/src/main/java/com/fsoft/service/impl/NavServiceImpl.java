package com.fsoft.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.dto.NavDTO;
import com.fsoft.entity.NavEntity;
import com.fsoft.repository.NavRepository;
import com.fsoft.service.NavService;
import com.fsoft.service.RecordService;

@Service
public class NavServiceImpl implements NavService {

	@Autowired
	NavRepository navRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RecordService recordSevice;

	@Override
	public List<NavDTO> getAllNav() {
		List<NavEntity> listNav = navRepository.findAll();
		List<NavDTO> listNavDTO = modelMapper.map(listNav, new TypeToken<List<NavDTO>>() {
		}.getType());
		return listNavDTO;
	}

	@Override
	public NavDTO findNavByID(String id) {
		NavEntity navEn = navRepository.findById(id).get();
		NavDTO dto = modelMapper.map(navEn, NavDTO.class);
		return dto;
	}

	@Override
	public void updateNav(NavDTO nav, String author) {
		NavEntity navEn = modelMapper.map(nav, NavEntity.class);

		try {
			navRepository.save(navEn);
			recordSevice.recordUpdate(nav.getNavID(), author, " update navigation",
					"Completed");
		} catch (Exception ex) {
			recordSevice.recordUpdate(nav.getNavID(), author, " update navigation",
					"Failed");
		}
	}

	@Override
	public void addNewNav(NavDTO nav, String author) {
		NavEntity navEn = modelMapper.map(nav, NavEntity.class);
		try {
			navRepository.save(navEn);
			recordSevice.recordUpdate(nav.getNavID(), author, " add new navigation",
					"Completed");
		} catch (Exception ex) {
			recordSevice.recordUpdate(nav.getNavID(), author, " add new navigation",
					"Failed");
		}
	}

	@Override
	public boolean deleteNav(String id, String author) {
		try {
			navRepository.deleteById(id);
			recordSevice.recordUpdate(id, author, " delete navigation",
					"Completed");
		} catch (IllegalArgumentException ex) {
			recordSevice.recordUpdate(id, author, " delete navigation",
					"Failed");
			return false;

		}
		return true;
	}
	@Override
	public List<NavDTO> findAllNav() {
		List<NavEntity>	listNavEntity = navRepository.findAll();
		List<NavDTO> listNavDTO = modelMapper.map(listNavEntity, new TypeToken<List<NavDTO>>() {
		}.getType());
		return listNavDTO;
	}
	
	
}
