package com.fsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.dto.BlogDTO;
import com.fsoft.dto.CommentDTO;
import com.fsoft.service.BlogService;
import com.fsoft.service.MainService;

@Controller
public class BlogController {

	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	public MainService mainService;
	
	@GetMapping("/totalblog")
	public String getAllBlog(Model model) {
		Page<BlogDTO> pageBlog = blogService.findAllBlogPaging(1);
		List<BlogDTO> listBlog = pageBlog.getContent();
		
		model.addAttribute("CURRENT_PAGE",1);
		
		model.addAttribute("TOTAL_PAGE",pageBlog.getTotalPages());
		
		model.addAttribute("TOTAL_ITEMS", pageBlog.getTotalElements());
		
		model.addAttribute("LIST_BLOG", listBlog);
		return "ADMIN/blog";
	}
	
	@GetMapping("/totalblog/page/{pageNumber}")
	public String getAllBlog(Model model, @PathVariable("pageNumber") int pageNumber) {
		Page<BlogDTO> pageBlog = blogService.findAllBlogPaging(pageNumber);
		List<BlogDTO> listBlog = pageBlog.getContent();
		
		model.addAttribute("CURRENT_PAGE",pageNumber);
		
		model.addAttribute("TOTAL_PAGE",pageBlog.getTotalPages());
		
		model.addAttribute("TOTAL_ITEMS", pageBlog.getTotalElements());
		
		model.addAttribute("LIST_BLOG", listBlog);
		
		return "ADMIN/blog";
	}
	
//	@GetMapping("detailsblog/{titleBlog}")
//	public String getBlog(Model model, @PathVariable("titleBlog") String titleBlog) {
//		model.addAttribute("TITLE_BLOG", "/blog/design-pattern-php-adapter-pattern-337");
//		return "ADMIN/detailsblog";
//		
//	}
	
	@GetMapping("login/deleteblog")
	public String getAllBlogDelete(Model model) {
		Page<BlogDTO> pageBlog = blogService.findAllBlogPaging(1);
		List<BlogDTO> listBlog = pageBlog.getContent();
		
		model.addAttribute("CURRENT_PAGE",1);
		
		model.addAttribute("TOTAL_PAGE",pageBlog.getTotalPages());
		
		model.addAttribute("TOTAL_ITEMS", pageBlog.getTotalElements());
		
		model.addAttribute("LIST_BLOG", listBlog);
		return "ADMIN/delete-blog";
	}
	
	@GetMapping("login/deleteblog/page/{pageNumber}")
	public String getBlogDelete(Model model, @PathVariable("pageNumber") int pageNumber) {
		Page<BlogDTO> pageBlog = blogService.findAllBlogPaging(pageNumber);
		List<BlogDTO> listBlog = pageBlog.getContent();
		
		model.addAttribute("CURRENT_PAGE",pageNumber);
		
		model.addAttribute("TOTAL_PAGE",pageBlog.getTotalPages());
		
		model.addAttribute("TOTAL_ITEMS", pageBlog.getTotalElements());
		
		model.addAttribute("LIST_BLOG", listBlog);
		
		return "ADMIN/delete-blog";
	}
	
	@ResponseBody
	@RequestMapping(value= "/login/delete-blog", method = RequestMethod.POST)
	public String delete(@RequestParam("BlogID") String id, @RequestParam("authorRecord") String authorRecord) {
		BlogDTO blog = blogService.findByBlogID(id);
		boolean result = blogService.deleteBlog(blog, authorRecord);
		if (result) {
			return "SUCCESS";
		} else {
			return "FAILED";
		}	
	}
	
	@GetMapping("/blogdetails/{link_content}")
	public String getBlogDetails(Model model, @PathVariable("link_content") String linkContent,
			@ModelAttribute("commentNe") CommentDTO commentDTO) {
		BlogDTO blogDetails = blogService.findByLinkBlog(linkContent);
		mainService.navDefault(model);
		model.addAttribute("BLOG_DETAILS", blogDetails);
		return "templateBlog";
	}
	
}
