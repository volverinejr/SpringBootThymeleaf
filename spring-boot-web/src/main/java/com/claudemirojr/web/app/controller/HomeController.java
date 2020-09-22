package com.claudemirojr.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	private String home() {
		//return "redirect:/app/index";
		return "forward:/app/index";
	}

}
