package com.fsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fsoft.dto.AdminDTO;
import com.fsoft.dto.BlogDTO;
import com.fsoft.service.AdminService;
import com.fsoft.service.BlogService;

@Controller
public class DashboardController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/dashboard")
	public String getAllBlog(Model model) {
		List<BlogDTO> listBlog = blogService.findAllBlog();
		List<BlogDTO> listBlogNewest = blogService.listBlogNewest();
		List<AdminDTO> listAdminDTOs = adminService.findAllUser();
		model.addAttribute("LIST_NEWEST_BLOG", listBlogNewest);
		model.addAttribute("LIST_BLOG", listBlog);
		model.addAttribute("LIST_ADMIN", listAdminDTOs);
		return "ADMIN/index-2";
	}
}
