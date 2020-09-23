package com.claudemirojr.form.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.claudemirojr.form.app.model.entity.Pais;
import com.claudemirojr.form.app.model.entity.Role;
import com.claudemirojr.form.app.model.entity.Usuario;
import com.claudemirojr.form.app.service.IPaisService;
import com.claudemirojr.form.app.service.IRoleService;
import com.claudemirojr.form.app.validator.UsuarioValidator;

@Controller
@SessionAttributes("usuario")
public class FormController {

	@Autowired
	private UsuarioValidator validador;

	@Autowired
	private IPaisService _servicePais;

	@Autowired
	private IRoleService _serviceRoles;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
	}

	@ModelAttribute("paises")
	public List<String> popularPaises() {
		List<String> paises = new ArrayList<>();

		paises.add("Brasil");
		paises.add("México");
		paises.add("Chile");

		return paises;
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> popularPaisesMap() {
		Map<String, String> paises = new HashMap<>();

		paises.put("BR", "Brasil");
		paises.put("MX", "México");
		paises.put("CL", "Chile");

		return paises;
	}

	@ModelAttribute("paisesLista")
	public List<Pais> popularPaisesLista() {
		return _servicePais.listar();
	}

	@ModelAttribute("rolesString")
	public List<String> popularRoles() {
		List<String> roles = new ArrayList<>();

		roles.add("ROLE_ADMIN");
		roles.add("ROLE_BASICO");
		roles.add("ROLE_LEITOR");

		return roles;
	}

	@ModelAttribute("rolesMap")
	public Map<String, String> popularRolesMap() {
		Map<String, String> roles = new HashMap<>();

		roles.put("ROLE_ADMIN", "Administrador");
		roles.put("ROLE_BASICO", "Básico");
		roles.put("ROLE_LEITOR", "Leitor");

		return roles;
	}

	@ModelAttribute("rolesLista")
	public List<Role> popularRolesLista() {
		return _serviceRoles.listar();
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNome("Claudemiro");
		usuario.setApelido("Miro");
		usuario.setIdentificador("123.456.789-AB");
		usuario.setHabilitar(true);

		usuario.setPais(new Pais(2, "MX", "México"));

		model.addAttribute("titulo", "Formulário Usuário");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@PostMapping("/form")
	public String processar(@Valid Usuario usuario, BindingResult result, Model model) {

		// validador.validate(usuario, result);

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulário com Erro de Validação");
			return "form";
		}

		return "redirect:/ver";
	}

	@GetMapping("/ver")
	public String ver(@SessionAttribute(name = "usuario", required = false) Usuario usuario, Model model,
			SessionStatus status) {

		if (usuario == null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado do Formulário");

		status.setComplete();

		return "resultado";
	}

}
