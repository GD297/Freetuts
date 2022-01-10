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

import com.fsoft.dto.SubCateDTO;
import com.fsoft.entity.CateEntity;
import com.fsoft.repository.CategoryRepository;
import com.fsoft.service.SubCateService;

@Controller
public class SubCategoryController {

	@Autowired
	private SubCateService subCateService;

	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("subCategory")
	public String getSubCategory(Model model, @PathParam("message") String message) {
		if (message != null) {
			if (message.equals("SUCCESS")) {
				model.addAttribute("SUCCESS", "Update sub-category successfully!!");
			}
		}
		List<SubCateDTO> list = subCateService.findAllSubCate();
		model.addAttribute("listSubCate", list);
		return "ADMIN/list-subcate";
	}

	@GetMapping("add-subCategory")
	public String addCategory(Model model, @ModelAttribute("subCate") SubCateDTO dto) {
		List<CateEntity> listCate = categoryRepository.findAll();
		model.addAttribute("listCate", listCate);
		return "ADMIN/add-subcate";
	}

	@PostMapping("add-subCategory")
	public String addCategory1(Model model, @ModelAttribute("subCate") SubCateDTO dto,
			@RequestParam("authorRecord") String authorRecord, @RequestParam("editor1") String content,
			@RequestParam("author") String author) {
		String url;
		try {
			dto.setContent(content);
			dto.setAuthor(author);
			Date date = new Date();
			Timestamp time = new Timestamp(date.getTime());
			dto.setCreateDate(time);
			subCateService.addSubCate(dto, author);
			url = "redirect:/subCategory?message=SUCCESS";
		} catch (Exception ex) {
			model.addAttribute("FAILED", "Add Sub-Category failed!!");
			url = "ADMIN/add-subcate";
		}
		return url;
	}

	@GetMapping("edit-subCategory/{id}")
	public String editSubCategory(Model model, @PathVariable("id") String id) {
		List<CateEntity> listCate = categoryRepository.findAll();
		model.addAttribute("listCate", listCate);
		SubCateDTO dto = subCateService.findSubCateByID(id);
		model.addAttribute("subCate", dto);
		return "ADMIN/add-subcate";
	}

	@PostMapping("edit-subCategory")
	public String edit(@ModelAttribute("subCate") SubCateDTO cate, Model model,
			@RequestParam("authorRecord") String author) {
		String url;
		try {
			subCateService.updateSubCate(cate, author);
			url = "redirect:/subCategory?message=SUCCESS";
		} catch (Exception ex) {
			model.addAttribute("FAILED", "Update Sub-Category failed!!");
			url = "ADMIN/add-subcate";
		}

		return url;
	}

	@ResponseBody
	@RequestMapping(value = "delete-subCategory", method = RequestMethod.POST)
	public String delete(@RequestParam("SubCateID") String id, Model model,
			@RequestParam("authorRecord") String author) {
		boolean result = subCateService.deleteSubCate(id, author);
		if (result) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}
	}
}
