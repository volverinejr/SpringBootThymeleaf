package com.claudemirojr.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/variaveis")
public class VariaveisController {
	
	@GetMapping("/string/{texto}")
	private String variaveis(@PathVariable String texto, Model model) {
		
		
		model.addAttribute("texto", texto);

		return "variaveis/ver";
	}

}
