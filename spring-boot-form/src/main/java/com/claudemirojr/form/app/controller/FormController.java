package com.claudemirojr.form.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.claudemirojr.form.app.model.entity.Usuario;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo", "Formulário Usuário");
		model.addAttribute("usuario", new Usuario());
		return "form";
	}

	@PostMapping("/form")
	public String processar(@Valid Usuario usuario, BindingResult result, Model model) {

		model.addAttribute("titulo", "Resultado do Formulário");
		if (result.hasErrors()) {
			Map<String, String> erros = new HashMap<>();

			result.getFieldErrors().forEach(err -> {
				erros.put(err.getField(), err.getDefaultMessage());
			});

			model.addAttribute("erros", erros);

			return "form";
		}

		model.addAttribute("usuario", usuario);

		return "resultado";
	}

}
