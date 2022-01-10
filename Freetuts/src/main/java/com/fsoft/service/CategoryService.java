package com.fsoft.service;

import java.util.List;

import com.fsoft.dto.CateDTO;

public interface CategoryService {

	List<CateDTO> getAllCate(String NavID);

	List<CateDTO> getAllCateByNavID(String navID);
	
	String addCategory(CateDTO cateDto, String author);
	
	String updateCategory(CateDTO cateDto, String author);
	
	boolean deleteCategory(String id, String author);
	
	List<CateDTO> findAllCate();
	
	CateDTO findCateByID(String id);
	
//	List<CateDTO> findAllByNavIDLike(String navID);

	CateDTO getCateByCateID(String id);
}

