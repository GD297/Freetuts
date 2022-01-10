package com.fsoft.service;

import java.util.List;

import com.fsoft.dto.NavDTO;

public interface NavService {

	List<NavDTO> getAllNav();
	
	NavDTO findNavByID(String id);
	
	void updateNav(NavDTO nav, String author);
	
	void addNewNav(NavDTO nav, String author);

	boolean deleteNav(String id, String author);

	List<NavDTO> findAllNav();
}
