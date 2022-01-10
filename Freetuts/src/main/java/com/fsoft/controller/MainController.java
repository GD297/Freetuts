package com.fsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fsoft.dto.BlogDTO;
import com.fsoft.dto.CateDTO;
import com.fsoft.dto.CategorizeDTO;
import com.fsoft.dto.CommentDTO;
import com.fsoft.dto.SubCateDTO;
import com.fsoft.service.BlogService;
import com.fsoft.service.CategorizeService;
import com.fsoft.service.CategoryService;
import com.fsoft.service.MainService;
import com.fsoft.service.NavService;
import com.fsoft.service.SubCateService;

@Controller
public class MainController {
	
	@Autowired
	public BlogService blogService;
	
	@Autowired
	public CategoryService categoryService;
	
	@Autowired
	public CategorizeService categorizeService;
	
	@Autowired
	public SubCateService subCateService;
	
	@Autowired
	public NavService navService;
	
	@Autowired
	public MainService mainService;
	
	@GetMapping("/template")
	public String showDetail(@RequestParam("id") String id, Model model) {
		mainService.navDefault(model);
		model.addAttribute("commentNe", new CommentDTO());
		//Find id in Categorize
		CategorizeDTO categorizeDTO = categorizeService.getCategorizeByID(id);
		if (categorizeDTO != null) {
			model.addAttribute("CATEGORIZE", categorizeDTO);
			return "templateCategorize";
			
		} else {
			//Find id in Category
			CateDTO cateDTO = categoryService.getCateByCateID(id);
			
			if (cateDTO != null) {
				model.addAttribute("CATE", cateDTO);
				return "templateCate";
				
			} else {
				//Find id in Sub-Category
				SubCateDTO subCateDTO = subCateService.getSubCateByID(id);
				
				if (subCateDTO != null) {
					
					model.addAttribute("SUBCATE", subCateDTO);
					return "templateSubCate";
					
				} else {
					//Find id in blog
					BlogDTO blogDTO = blogService.getBlogByID(id);
					if (blogDTO != null) {
						model.addAttribute("BLOG_DETAILS", blogDTO);
						return "templateBlog";
						
					} else {
						mainService.coupon(model);
						mainService.homePage(model);
						return "homePage";
						
					}
				}
			}
		}
	}
}
