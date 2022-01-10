package com.fsoft.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fsoft.dto.BlogDTO;

public interface BlogService {
	
	List<BlogDTO> getAllCoupon(String type);
	
	List<BlogDTO> getTop5(String type);
	
	String addBlog(BlogDTO dto, String author);
	
	boolean deleteBlog(BlogDTO blog, String author);
	
	List<BlogDTO> findAllBlog();
	
	Page<BlogDTO> findAllBlogPaging(int pageNum);
	
	List<BlogDTO> listBlogNewest();

	BlogDTO findByBlogID(String id);

	boolean updateBlog(BlogDTO blog, String author);

	BlogDTO findByLinkBlog(String link);
	
	BlogDTO getBlogByID(String id);

}
