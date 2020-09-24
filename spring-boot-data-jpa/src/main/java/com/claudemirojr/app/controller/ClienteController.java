package com.claudemirojr.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.claudemirojr.app.model.entity.Cliente;
import com.claudemirojr.app.model.service.ClienteService;

@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listar")
	public String listar(Model model) {

		List<Cliente> clientes = clienteService.findAll();

		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", clientes);

		return "cliente/listar";
	}

	@GetMapping("/form")
	public String criar(Model model) {
		Cliente cliente = new Cliente();

		model.addAttribute("titulo", "Formulário de Cliente");
		model.addAttribute("cliente", cliente);

		return "cliente/form";
	}

	@PostMapping("/form")
	public String cadastrar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulário de Cliente");
			return "cliente/form";
		}

		clienteService.save(cliente);
		
		status.isComplete();

		return "redirect:listar";
	}
	
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.findById(id);

		model.addAttribute("titulo", "Editando o Cliente");
		model.addAttribute("cliente", cliente);

		return "cliente/form";
	}
	
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		clienteService.deleteById(id);

		return "redirect:listar";
	}
	
	
	
	

}
