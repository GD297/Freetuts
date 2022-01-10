package com.fsoft.service;

import java.util.List;

import com.fsoft.dto.SubCateDTO;

public interface SubCateService {

	List<SubCateDTO> findAllSubCate();

	void addSubCate(SubCateDTO dto, String author);

	void updateSubCate(SubCateDTO dto, String author);

	boolean deleteSubCate(String id, String author);

	SubCateDTO findSubCateByID(String id);

	List<SubCateDTO> listSub();
	
	SubCateDTO getSubCateByID(String id);

}
