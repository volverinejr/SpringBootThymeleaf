package com.claudemirojr.error.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.claudemirojr.error.app.exception.UsuarioNaoEncontradoException;
import com.claudemirojr.error.app.model.entity.Usuario;
import com.claudemirojr.error.app.model.service.UsuarioService;

@Controller
public class AppController {
	
	
	@Autowired
	private UsuarioService _service;
	
	@GetMapping("/index")
	public String index(Model model) {
		
		//Integer valor = 1/0;
		
		Integer valor = Integer.parseInt("abc");
		
		model.addAttribute("titulo", "Tratando os Erros");
		
		return "index";
	}
	
	
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model) {
		
		Usuario usuario = _service.findById(id);
		
		if (usuario == null) {
			throw new UsuarioNaoEncontradoException(id.toString());
		}
		
		model.addAttribute("titulo", "Detalhe do usuário: ".concat( usuario.getNome() ));
		model.addAttribute("usuario", usuario);
		
		return "ver";
	}

}
