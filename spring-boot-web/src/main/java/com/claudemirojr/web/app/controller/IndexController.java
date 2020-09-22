package com.claudemirojr.web.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.claudemirojr.web.app.model.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {

	@GetMapping({ "", "/", "/index", "/home" })
	public String index(Model model) {

		model.addAttribute("titulo", "Olá Spring Framework!");

		return "index";
	}

	@GetMapping("/perfil")
	private String perfil(Model model) {

		Usuario usuario = new Usuario("Claudemiro", "Miro", "volverinejr@gmail.com");

		model.addAttribute("titulo", "Perfil do Usuário: ".concat(usuario.getNome()));
		model.addAttribute("usuario", usuario);

		return "perfil";
	}

	@GetMapping("/listar")
	private String listar(Model model) {

		model.addAttribute("titulo", "Listando os Usuários");

		return "listar";
	}

	@ModelAttribute("usuarios")
	public List<Usuario> popularUsuario() {
		List<Usuario> usuarios = new ArrayList<>();

		usuarios.add(new Usuario("Claudemiro", "Miro", "volverinejr@gmail.com"));
		usuarios.add(new Usuario("Jamile", "Jam", "jamile@gmail.com"));

		return usuarios;
	}

}
