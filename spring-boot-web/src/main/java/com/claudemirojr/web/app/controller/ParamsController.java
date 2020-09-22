package com.claudemirojr.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/params")
public class ParamsController {

	@GetMapping("/")
	private String index(Model model) {
		return "params/index";
	}

	@GetMapping("/string")
	private String param(@RequestParam(defaultValue = "Não informado") String texto, Model model) {
		model.addAttribute("resultado", "texto enviado: " + texto);

		return "params/ver";
	}

	@GetMapping("/mix-params")
	private String param(
			@RequestParam(defaultValue = "Não informado") String texto,
			@RequestParam(defaultValue = "0") Integer valor,
			Model model) {
		model.addAttribute("resultado", "texto enviado: " + texto + ", Valor: " + valor);

		return "params/ver";
	}

}
