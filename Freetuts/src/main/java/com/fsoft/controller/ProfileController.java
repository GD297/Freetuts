package com.fsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fsoft.dto.AdminDTO;
import com.fsoft.dto.SkillsAdminDTO;
import com.fsoft.repository.AdminRepository;
import com.fsoft.service.AdminService;
import com.fsoft.service.SkillsAdminService;

@Controller
public class ProfileController {
	
	@Autowired
	private SkillsAdminService skillsAdminService;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("profile")
	public String profile(Authentication authentication ,Model model) {
		List<SkillsAdminDTO> listSkillsAdmin = skillsAdminService.getAllSkillsAdmin(authentication.getName()); 	
		model.addAttribute("skills", listSkillsAdmin);
		AdminDTO adminDto = adminService.findByUsername(authentication.getName());
		model.addAttribute("adminDto", adminDto);
		return "ADMIN/profile";
	}

	
//	@PostMapping("update-profile")
//	public String updateProfile(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname, @RequestParam("mail") String email,
//			 @RequestParam("phone") long phone, @RequestParam("bio") String bio, @RequestParam("username") String username) {
//		adminRepository.setUpdateProfile(firstname, lastname, email, phone, bio, username);
//		return "redirect:profile";
//	}
	
	@PostMapping("update-profile")
	public String updateProfile(@ModelAttribute("adminDto") AdminDTO adminDto) {
		adminRepository.setUpdateProfile(adminDto.getFirstname(), adminDto.getLastname(), adminDto.getMail(), adminDto.getPhone(), adminDto.getBio(), adminDto.getUsername());
		return "redirect:profile";
	}
}
