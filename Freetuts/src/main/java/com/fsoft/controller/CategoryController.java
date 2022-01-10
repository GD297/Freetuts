package com.fsoft.controller;

import java.sql.Timestamp;
import java.util.Date;
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

import com.fsoft.dto.CateDTO;
import com.fsoft.dto.NavDTO;
import com.fsoft.service.CategoryService;
import com.fsoft.service.NavService;

@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private NavService navService;

	@GetMapping("category")
	public String getListCate(Model model, @PathParam("message") String message) {
		if (message != null) {
			if (message.equals("SUCCESS")) {
				model.addAttribute("SUCCESS", "Update category successfully!!");
			}
		}
		// Load all Category
		List<CateDTO> list = categoryService.findAllCate();
		model.addAttribute("list_cate", list);
		// Load all Navigation
		List<NavDTO> listNav = navService.getAllNav();
		model.addAttribute("listNav", listNav);
		return "ADMIN/list-cate";
	}

	@GetMapping("add-category")
	public String addCategory1(Model model, @ModelAttribute("cateNe") CateDTO dto) {
		List<NavDTO> listNav = navService.getAllNav();
		model.addAttribute("listNav", listNav);
		return "ADMIN/add-cate";
	}

	@PostMapping("add-category")
	public String addCategory(Model model, @ModelAttribute("cateNe") CateDTO dto,
			@RequestParam("authorRecord") String authorRecord, @RequestParam("editor1") String content,
			@RequestParam("author") String author) {
		String url;
		dto.setAuthor(author);
		dto.setContent(content);
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		dto.setCreateDate(time);
		String result = categoryService.addCategory(dto, authorRecord);
		if (result.equals("SUCCESS")) {
			url = "redirect:/category?message=SUCCESS";
		}  else if (result.equals("DUPLICATE_LINK_CATE")) {
			List<NavDTO> listNav = navService.getAllNav();
			model.addAttribute("listNav", listNav);
			model.addAttribute("FAILED", "Duplicate link category");
			url = "ADMIN/add-cate";
		}else{
			List<NavDTO> listNav = navService.getAllNav();
			model.addAttribute("listNav", listNav);
			model.addAttribute("FAILED", "Add Category fail!!");
			url = "ADMIN/add-cate";
		}
		return url;
	}

	@GetMapping("edit-category/{cateID}")
	public String edit(Model model, @PathVariable("cateID") String id) {
		List<NavDTO> listNav = navService.getAllNav();
		model.addAttribute("listNav", listNav);
		CateDTO cateDTO = categoryService.findCateByID(id);
		model.addAttribute("cateNe", cateDTO);
		return "ADMIN/add-cate";
	}

	@PostMapping("edit-category")
	public String edit(@ModelAttribute("cateNe") CateDTO cate, Model model,
			@RequestParam("authorRecord") String author) {
		String url;
		try {
			categoryService.updateCategory(cate, author);
			url = "redirect:/category?message=SUCCESS";
		} catch (Exception ex) {
			model.addAttribute("FAILED", "Add Category fail!!");
			url = "ADMIN/add-nav";
		}
		return url;

	}

	@ResponseBody
	@RequestMapping(value = "/delete-category", method = RequestMethod.POST)
	public String delete(@RequestParam("CateID") String id, @RequestParam("authorRecord") String author) {

		boolean result = categoryService.deleteCategory(id, author);
		System.out.println("ASDASDASD" + result);
		if (result) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}

	}

	public void getAllNav(Model model) {
		List<NavDTO> listNav = navService.getAllNav();
		model.addAttribute("listNav", listNav);
	}
}
