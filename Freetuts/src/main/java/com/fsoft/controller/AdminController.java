package com.fsoft.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fsoft.dto.BlogDTO;
import com.fsoft.dto.CateDTO;
import com.fsoft.dto.SubCateDTO;
import com.fsoft.service.AdminService;
import com.fsoft.service.BlogService;
import com.fsoft.service.CategoryService;
import com.fsoft.service.SubCateService;

@Controller
@RequestMapping("login")
public class AdminController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	public AdminService adminService;

	@Autowired
	private BlogService blogService;

	@Autowired
	private SubCateService subCateService;
	


	@GetMapping("")
	public String login(Model model, String error, String logout, Authentication auth) {
		if(auth != null) {
			return "redirect:dashboard";
		}		
//		if (error != null)
//			model.addAttribute("error", "Your username and password is invalid.");
		
		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");

		return "ADMIN/login";
	}

	@GetMapping("/admin")
	public String admin(Model model) {
		return "ADMIN/index-2";
	}

	@GetMapping("/editblog")
	public String getAllBlog(Model model, @PathParam("message") String message) {
		if (message != null) {
			if (message.equals("SUCCESS")) {
				model.addAttribute("SUCCESS", "Edit blog complete!");
			}
		}
		Page<BlogDTO> pageBlog = blogService.findAllBlogPaging(1);
		List<BlogDTO> listBlog = pageBlog.getContent();

		model.addAttribute("CURRENT_PAGE", 1);

		model.addAttribute("TOTAL_PAGE", pageBlog.getTotalPages());

		model.addAttribute("TOTAL_ITEMS", pageBlog.getTotalElements());

		model.addAttribute("LIST_BLOG", listBlog);
		return "ADMIN/blank";
	}

	@GetMapping("/editblog/page/{pageNum}")
	public String getAllBlog(Model model, @PathVariable("pageNum") int pageNum) {
		Page<BlogDTO> pageBlog = blogService.findAllBlogPaging(pageNum);
		List<BlogDTO> listBlog = pageBlog.getContent();

		model.addAttribute("CURRENT_PAGE", pageNum);

		model.addAttribute("TOTAL_PAGE", pageBlog.getTotalPages());

		model.addAttribute("TOTAL_ITEMS", pageBlog.getTotalElements());

		model.addAttribute("LIST_BLOG", listBlog);
		return "ADMIN/blank";
	}

	@GetMapping("/newblog")
	public String admin(@ModelAttribute("blog") BlogDTO blog, Model model) {
		// Load all cate
		List<CateDTO> list = categoryService.findAllCate();
		model.addAttribute("LIST_CATE", list);

		// Load all subcate
		List<SubCateDTO> listSub = subCateService.listSub();
		model.addAttribute("LIST_SUBCATE", listSub);

		return "ADMIN/create-post";
	}

	@PostMapping("/createblog")
	public String createBlog(@RequestParam("authorRecord") String authorRecord, @ModelAttribute("blog") BlogDTO blog,
			@RequestParam("editor1") String content, @RequestParam("image") MultipartFile multipartFile,
			@RequestParam("author") String author, Model model) {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		blog.setCreateDate(time);
		blog.setLinkImage(fileName);
		blog.setAuthor(author);
		blog.setContent(content);
		String result = blogService.addBlog(blog, authorRecord);
		String url;
		if (result.equals("SUCCESS")) {
			url = "redirect:/dashboard";
		}  else if (result.equals("DUPLICATE_LINK_BLOG")) {
			// Load all cate
			List<CateDTO> list = categoryService.findAllCate();
			model.addAttribute("LIST_CATE", list);

			// Load all subcate
			List<SubCateDTO> listSub = subCateService.listSub();
			model.addAttribute("LIST_SUBCATE", listSub);
			model.addAttribute("FAILED", "Duplicate link blog");
			url = "ADMIN/create-post";
		}else{
			// Load all cate
			List<CateDTO> list = categoryService.findAllCate();
			model.addAttribute("LIST_CATE", list);

			// Load all subcate
			List<SubCateDTO> listSub = subCateService.listSub();
			model.addAttribute("LIST_SUBCATE", listSub);
			model.addAttribute("FAILED", "Add Blog fail!!");
			url = "ADMIN/create-post";
		}
		return url;
	}

	@GetMapping("/editblog/{idBlog}")
	public String editBlog(@PathVariable("idBlog") String idblog, Model model) {
		// Load blog by id
		BlogDTO blog = blogService.findByBlogID(idblog);
		model.addAttribute("BLOG_EDITING", blog);

		// Load all cate
		List<CateDTO> list = categoryService.findAllCate();
		model.addAttribute("LIST_CATE", list);

		// Load all subcate
		List<SubCateDTO> listSub = subCateService.listSub();
		model.addAttribute("LIST_SUBCATE", listSub);
		return "ADMIN/edit-post";
	}

	@PostMapping(value = "/editblog", produces = "multipart/form-data;charset=utf-8")
	public String editBlog(@ModelAttribute("BLOG_EDITING") BlogDTO blog, @RequestParam("content") String content,
			@RequestParam("image") MultipartFile multipartFile, Model model,
			@RequestParam("authorRecord") String author) {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if (fileName.isEmpty() || fileName == null) {
			fileName = blog.getLinkImage();
		}
		Date date = new Date();
		Timestamp time = new Timestamp(date.getTime());
		System.out.println(time);
		blog.setCreateDate(time);
		blog.setLinkImage(fileName);
		boolean result = blogService.updateBlog(blog, author);
		String url = "redirect:editblog?message=SUCCESS";
		if (!result) {
			url = "ADMIN/edit-post";
			model.addAttribute("FAILED", "Edit blog fail!!");
		}
		return url;
	}

}
