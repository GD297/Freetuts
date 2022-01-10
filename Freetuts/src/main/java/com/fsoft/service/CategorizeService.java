package com.fsoft.service;

import java.util.List;

import com.fsoft.dto.CategorizeDTO;


public interface CategorizeService {

	List<CategorizeDTO> getAllCategorize();
	
	CategorizeDTO getCategorizeByID(String id);
	
	CategorizeDTO getCategorizeByLinkCategorize(String link);
}
