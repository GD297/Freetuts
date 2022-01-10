package com.fsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fsoft.dto.BlogDTO;
import com.fsoft.dto.CateDTO;
import com.fsoft.dto.CategorizeDTO;
import com.fsoft.dto.NavDTO;

@Service
public class MainService {
	
	@Autowired
	public CategoryService cateService;
	
	@Autowired
	private NavService navService;
	
	@Autowired
	public BlogService blogService;
	
	@Autowired
	private CategorizeService categorizeService;
	
	public void navDefault(Model model) {
		List<CateDTO> listCate = cateService.findAllCate();
		model.addAttribute("CATE_LIST", listCate);
		List<NavDTO> listNav = navService.findAllNav();
		model.addAttribute("NAV_LIST", listNav);
		List<CategorizeDTO> listCategorize = categorizeService.getAllCategorize();
		model.addAttribute("CATEGORIZE_LIST", listCategorize);
//		List<CateDTO> cate03 = cateService.getAllCateByNavID("03");
//		List<CateDTO> cate04 = cateService.getAllCateByNavID("04");
//		List<CateDTO> cate05 = cateService.getAllCateByNavID("05");
//		List<CateDTO> cate06 = cateService.getAllCateByNavID("06");
//		List<CateDTO> cate07 = cateService.getAllCateByNavID("07");
//		List<CateDTO> cate08 = cateService.getAllCateByNavID("08");
//		List<CateDTO> cate09 = cateService.getAllCateByNavID("09");
//		List<CateDTO> cate10 = cateService.getAllCateByNavID("10");
//		List<CateDTO> cate21 = cateService.getAllCateByNavID("2.1");
//		List<CateDTO> cate22 = cateService.getAllCateByNavID("2.2");
//		List<CateDTO> cate23 = cateService.getAllCateByNavID("2.3");
//		List<CateDTO> cate24 = cateService.getAllCateByNavID("2.4");
//		List<CateDTO> cate25 = cateService.getAllCateByNavID("2.5");
//		List<CateDTO> cate26 = cateService.getAllCateByNavID("2.6");
//		List<CateDTO> cate41 = cateService.getAllCateByNavID("4.1");
//		List<CateDTO> cate71 = cateService.getAllCateByNavID("7.1");
//		model.addAttribute("CATE_LIST_03", cate03);
//		model.addAttribute("CATE_LIST_04", cate04);
//		model.addAttribute("CATE_LIST_05", cate05);
//		model.addAttribute("CATE_LIST_06", cate06);
//		model.addAttribute("CATE_LIST_07", cate07);
//		model.addAttribute("CATE_LIST_08", cate08);
//		model.addAttribute("CATE_LIST_09", cate09);
//		model.addAttribute("CATE_LIST_10", cate10);
//		model.addAttribute("CATE_LIST_21", cate21);
//		model.addAttribute("CATE_LIST_22", cate22);
//		model.addAttribute("CATE_LIST_23", cate23);
//		model.addAttribute("CATE_LIST_24", cate24);
//		model.addAttribute("CATE_LIST_25", cate25);
//		model.addAttribute("CATE_LIST_26", cate26);
//		model.addAttribute("CATE_LIST_41", cate41);
//		model.addAttribute("CATE_LIST_71", cate71);
	}
	
	public void coupon(Model model) {
		List<BlogDTO> coupon = blogService.getAllCoupon("coupon");
		model.addAttribute("COUPON", coupon);
	}
	
	public void homePage(Model model) {
		//LAP_TRINH
		List<BlogDTO> top5P = blogService.getTop5("programming");
		model.addAttribute("PROGRAMMING", top5P);

		//WEB_MIEN_PHI
		List<BlogDTO> top5F = blogService.getTop5("freeweb");
		model.addAttribute("FREEWEB", top5F);

		//QUAN_TRI_WEB
		List<BlogDTO> top5W = blogService.getTop5("webmaster");
		model.addAttribute("WEBMASTER", top5W);

		//TIN_HOC
		List<BlogDTO> top5C = blogService.getTop5("computing");
		model.addAttribute("COMPUTING", top5C);

		//THU_THUAT
		List<BlogDTO> top5T = blogService.getTop5("tips");
		model.addAttribute("TIPS", top5T);

		//DOWNLOAD
		List<BlogDTO> top5D = blogService.getTop5("download");
		model.addAttribute("DOWNLOAD", top5D);

		//MARKETING
		List<BlogDTO> top5M = blogService.getTop5("marketing");
		model.addAttribute("MARKETING", top5M);

		//KHAM_PHA
		List<BlogDTO> top5E = blogService.getTop5("explore");
		model.addAttribute("EXPLORE", top5E);

		//MON_HOC
		List<BlogDTO> top5S = blogService.getTop5("subject");
		model.addAttribute("SUBJECT", top5S);
		
		//REVIEW_KHOAHOC
		List<BlogDTO> top5R = blogService.getTop5("review_course");
		model.addAttribute("REVIEW_COURSE", top5R);
		
		//BAI-TAP
		List<BlogDTO> top20E = blogService.getTop5("exercise");
		model.addAttribute("EXERCISE", top20E);
	}
}
