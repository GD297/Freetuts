package com.fsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fsoft.service.MainService;

@Controller
public class ComputingController {
	
	@Autowired
	public MainService mainService;
	
	@GetMapping("tin-hoc")
	public String tinHoc(Model model) {
		mainService.navDefault(model);
		return "tin-hoc";
	}

	@GetMapping("hoc-access")
	public String hocAccess(Model model) {
		mainService.navDefault(model);
		return "tin-hoc/hoc-access";
	}
	
	@GetMapping("hoc-excel")
	public String hocExcel(Model model) {
		mainService.navDefault(model);
		return "tin-hoc/hoc-excel";
	}
	
	@GetMapping("hoc-photoshop")
	public String hocPhotoshop(Model model) {
		mainService.navDefault(model);
		return "tin-hoc/hoc-photoshop";
	}
	
	@GetMapping("hoc-powerpoint")
	public String hocPowerPoint(Model model) {
		mainService.navDefault(model);
		return "tin-hoc/hoc-powerpoint";
	}
	
	@GetMapping("hoc-word")
	public String hocWord(Model model) {
		return "tin-hoc/hoc-word";
	}
}
