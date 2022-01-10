package com.fsoft.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsoft.dto.CategorizeDTO;
import com.fsoft.entity.CategorizeEntity;
import com.fsoft.repository.CategorizeRepository;
import com.fsoft.service.CategorizeService;

@Service
public class CategorizeServiceImpl implements CategorizeService {

	@Autowired
	CategorizeRepository categorizeRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<CategorizeDTO> getAllCategorize() {
		List<CategorizeEntity> listCategorize = categorizeRepo.findAll();
		List<CategorizeDTO> listCategorizeDTO = modelMapper.map(listCategorize, new TypeToken<List<CategorizeDTO>>() {
		}.getType());
		return listCategorizeDTO;
	}

	@Override
	public CategorizeDTO getCategorizeByID(String id) {
		CategorizeDTO categorizeDTO = null;
		try {
			CategorizeEntity entity = categorizeRepo.findById(id).get();
			categorizeDTO = modelMapper.map(entity, CategorizeDTO.class);
		} catch (Exception e) {
			return null;
		}
		return categorizeDTO;
	}

	@Override
	public CategorizeDTO getCategorizeByLinkCategorize(String link) {
//		CategorizeEntity categorize = categorizeRepo.findByLinkType(link);
//		CategorizeDTO categorizeDTO = modelMapper.map(categorize, CategorizeDTO.class);
//		
		return null;
	}
}
