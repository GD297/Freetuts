package com.fsoft.service.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fsoft.dto.BlogDTO;
import com.fsoft.entity.BlogEntity;
import com.fsoft.repository.BlogRepository;
import com.fsoft.service.BlogService;
import com.fsoft.service.RecordService;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ModelMapper modelMapper;
		
	@Autowired
	private RecordService recordService;

	@Override
	public List<BlogDTO> getAllCoupon(String type) {
		// TODO Auto-generated method stub
		List<BlogEntity> listBlog = blogRepository.findAllByTypeOrderByCreateDateDesc(type);
		List<BlogDTO> listBlogDTO = modelMapper.map(listBlog, new TypeToken<List<BlogDTO>>() {}.getType());

		return listBlogDTO;
	}

	@Override
	public List<BlogDTO> getTop5(String type) {
		// TODO Auto-generated method stub
		List<BlogEntity> listBlog = blogRepository.findAllByTypeOrderByCreateDateDesc(type);

		List<BlogDTO> listBlogDTO = modelMapper.map(listBlog, new TypeToken<List<BlogDTO>>() {}.getType());

		return listBlogDTO;
	}
	
	@Override
	public String addBlog(BlogDTO dto, String author) {
		try {
			// Check blog link exits
			BlogEntity blog = blogRepository.findByLinkContent(dto.getLinkContent());
			if (blog == null) {
				dto.setBlogID(UUID.randomUUID().toString());
				String linkContent = dto.getLinkContent();
				if(linkContent.contains("/")) {
					dto.setLinkContent(linkContent.replace("/", ""));
				}
				BlogEntity entity = modelMapper.map(dto, BlogEntity.class);
				
				blogRepository.save(entity);
				recordService.recordUpdate(dto.getBlogID(), author, " add new blog", "Completed");
				
				return "SUCCESS";
			} else {
				recordService.recordUpdate(dto.getBlogID(), author, " add new blog", "Failed");
				return "DUPLICATE_LINK_BLOG";
			} // End if blog link already in DB
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("Something went wrong, rollback transactions");
			recordService.recordUpdate(dto.getBlogID(), author, " add new blog", "Failed");
			return "FAILED";
		}

	}

	@Override
	public boolean deleteBlog(BlogDTO blog, String author) {
		try {
		blog.setStatus("DELETE");
		BlogEntity blogEn = modelMapper.map(blog, BlogEntity.class);
		blogRepository.save(blogEn);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public List<BlogDTO> findAllBlog() {
		List<BlogEntity> listBlogEntity = blogRepository.findAllByOrderByCreateDateDesc();
		List<BlogDTO> listBlogDTO = modelMapper.map(listBlogEntity, new TypeToken<List<BlogDTO>>() {}.getType());
		return listBlogDTO;
	}

	@Override
	public List<BlogDTO> listBlogNewest() {
		Calendar cal = Calendar.getInstance();
		//get current time
		cal.add(Calendar.DAY_OF_MONTH, -1);
		java.util.Date date = cal.getTime();
		java.sql.Date dateStart = new Date(date.getTime());
		System.out.println("ASD"+dateStart);
		List<BlogEntity> listBlogEntity = blogRepository.findByCreateDateAfter(dateStart);
		List<BlogDTO> listBlogDTO = modelMapper.map(listBlogEntity, new TypeToken<List<BlogDTO>>() {}.getType());
		System.out.println("DDSA"+listBlogDTO.size());
		return listBlogDTO;
	}

	@Override
	public Page<BlogDTO> findAllBlogPaging(int pageNum) {

		int pageSize = 12;
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, Sort.by("createDate").descending());
			Page<BlogEntity> page = blogRepository.findAll(pageable);
		Page<BlogDTO> pageDTO = modelMapper.map(page, new TypeToken<Page<BlogDTO>>() {}.getType());
		
		return pageDTO;
	}

	
	@Override
	public BlogDTO findByBlogID(String id) {
		BlogEntity blog = blogRepository.findByBlogID(id);
		BlogDTO blogDTO = modelMapper.map(blog, BlogDTO.class);
		return blogDTO;
	}


	@Override
	public boolean updateBlog(BlogDTO blog, String author) {
		try {
		BlogEntity blogEn = modelMapper.map(blog, BlogEntity.class);
		blogRepository.save(blogEn);
		recordService.recordUpdate(blog.getBlogID(), author, " update blog","Completed");
		}catch(Exception ex) {
			recordService.recordUpdate(blog.getBlogID(), author, " update blog","Failed");
			return false;
		}
		
		return true;
	}

	@Override
	public BlogDTO findByLinkBlog(String link) {
		BlogEntity blogDetails = blogRepository.findByLinkContent(link);
		BlogDTO listBlogDTOs = modelMapper.map(blogDetails, BlogDTO.class);
		return listBlogDTOs;
	}
	@Override
	public BlogDTO getBlogByID(String id) {
		BlogDTO blogDTO = null;
		try {
			BlogEntity entity = blogRepository.findById(id).get();
			blogDTO = modelMapper.map(entity, BlogDTO.class);
		} catch (Exception e) {
			return null;
		}
		return blogDTO;
	}
}
