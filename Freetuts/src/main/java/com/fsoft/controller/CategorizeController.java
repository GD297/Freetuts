package com.fsoft.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fsoft.dto.CategorizeDTO;
import com.fsoft.service.CategorizeService;

@Controller
public class CategorizeController {

	@Autowired
	CategorizeService categorizeService;
	
	@GetMapping("/categorize")
	public ResponseEntity<List<CategorizeDTO>> getCompanyList() {
		return new ResponseEntity<List<CategorizeDTO>>(categorizeService.getAllCategorize(), HttpStatus.OK);
	}
	
	@GetMapping("/categorize/{linkCrize}")
	public String getCategorizeDetails(@PathParam("linkCrize") String linkCategorize,Model model) {
		CategorizeDTO crize = categorizeService.getCategorizeByLinkCategorize(linkCategorize);
		model.addAttribute("CATEGORIZE", crize);
		return "templateCategorize";
	}
	
}
