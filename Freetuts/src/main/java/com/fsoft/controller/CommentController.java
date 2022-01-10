package com.fsoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsoft.dto.CommentDTO;
import com.fsoft.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService service;

	@ResponseBody
	@RequestMapping(value = "/post-comment", method = RequestMethod.POST)
	public String postComment(@ModelAttribute("commentNe") CommentDTO dto, @RequestParam("ID") String blogID) {
		try {
			service.addComment(dto, blogID);
			return "SUCCESS";
		} catch (Exception ex) {
			return "FAILED";
		}
	}

}
