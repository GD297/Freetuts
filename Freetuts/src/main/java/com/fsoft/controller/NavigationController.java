package com.fsoft.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.dto.NavDTO;
import com.fsoft.service.NavService;

@Controller
public class NavigationController {
	
	@Autowired
	private NavService navService;
	
	@GetMapping("/navigation")
	public String getAllNav(Model model, @PathParam("message") String message) {
		if (message != null) {
			if (message.equals("SUCCESS")) {
				model.addAttribute("SUCCESS", "Add navigation successfully!!");
			}
		}
		List<NavDTO> listNav = navService.getAllNav();
		model.addAttribute("LIST_NAV", listNav);
		return "ADMIN/list-nav";
	}
	
	@GetMapping("/edit-nav/{id}")
	public String updateNavPage(@PathVariable("id") String id, Model model) {
		NavDTO navDTO = navService.findNavByID(id);
		model.addAttribute("NAVIGATION", navDTO);
		return "ADMIN/add-nav";
		
	}

	@RequestMapping(value= "/edit-nav", method = RequestMethod.POST)
	public String updateNav(@ModelAttribute("NAVIGATION") NavDTO nav, Model model, @RequestParam("authorRecord") String author) {
		String url;
		try {
		navService.updateNav(nav, author);
		model.addAttribute("SUCCESS", "Update navigation successfully!");
		url = "redirect:/navigation?message=SUCCESS";
		}catch(Exception ex) {
			model.addAttribute("Failed", "Update navigation fail!");
			url = "ADMIN/add-nav";
		}
	
		return url;
		
	}
	
	@PostMapping("add-nav")
	public String addCategory(Model model, @ModelAttribute("NAVIGATION") NavDTO dto 
			, @RequestParam("authorRecord") String author) {
		String url;
		try {
			navService.addNewNav(dto, author);
			url = "redirect:/navigation?message=SUCCESS";
		} catch (Exception ex) {
			System.out.println(ex);
			model.addAttribute("FAILED", "Add navigation fail!!");
			url = "ADMIN/add-nav";
		}
		return url;
	}
	@GetMapping("add-nav")
	public String addCategoryPage(Model model, @ModelAttribute("NAVIGATION") NavDTO dto) {
		
		return "ADMIN/add-nav";
	}
	
	@ResponseBody
	@RequestMapping(value= "/delete-nav", method = RequestMethod.POST)
	public String delete(@RequestParam("NavId") String id 
			, @RequestParam("authorRecord") String author) {
		boolean result = navService.deleteNav(id, author);
		if (result) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}
		
	}
	
}
