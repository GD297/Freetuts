package com.fsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fsoft.dto.CommentDTO;
import com.fsoft.service.MainService;

@Controller
public class HomeController {
	
	@Autowired
	public MainService mainService;
	
	@GetMapping("")
	public String showAllCate(Model model) {
		mainService.navDefault(model);
		mainService.homePage(model);
		mainService.coupon(model);
		model.addAttribute("commentNe", new CommentDTO());
		return "homePage";
		
	}
	
}
