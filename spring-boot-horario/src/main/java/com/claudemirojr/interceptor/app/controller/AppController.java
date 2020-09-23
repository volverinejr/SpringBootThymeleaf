package com.claudemirojr.interceptor.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
	
	@GetMapping({"/", "/index"})
	public String index(Model model) {
		
		model.addAttribute("titulo", "Bem vindo ao horário de atendimento ao cliente");
		
		return "index";
	}
	
	
	@GetMapping("/fechado")
	public String fechado(Model model) {
		
		model.addAttribute("titulo", "Desculpe, estamos fechados");

		model.addAttribute("horario", "Abertura às 8hs e fechamento as 14hs");
		
		return "fechado";
	}
	

}
