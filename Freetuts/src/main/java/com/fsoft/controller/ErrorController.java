package com.fsoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

	@GetMapping("/403")
	public String accessDenied403() {
		return "error/error-403";
	}
	
	@GetMapping("/404")
	public String pageNotFound404() {
		return "error/error-404";
	}
	
	@GetMapping("/500")
	public String internalServerError500() {
		return "error/error-500";
	}
	
	@GetMapping("/408")
	public String requestTimeout408() {
		return "error/error-408";
	}
	
	@GetMapping("/400")
	public String badRequest400() {
		return "error/error-400";
	}
	
	@GetMapping("/405")
	public String methodNotAllow405() {
		return "error/error-405";
	}
	
	@GetMapping("/505")
	public String HTTPVersionnotSupported505() {
		return "error/error-505";
	}
}
