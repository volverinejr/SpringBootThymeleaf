package com.claudemirojr.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claudemirojr.app.model.entity.Cliente;
import com.claudemirojr.app.model.service.ClienteService;
import com.claudemirojr.app.util.paginator.PageRender;

@Controller
@RequestMapping("/clientes")
@SessionAttributes("cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listar")
	public String listar(
			@RequestParam(defaultValue = "0") int page, 
			@RequestParam(defaultValue = "5") int size, 
			Model model) {
		
		Pageable pageable = PageRequest.of(page, size);

		Page<Cliente> clientes = clienteService.findAll(pageable);
		
		
		PageRender<Cliente> pageRender = new PageRender<>("/clientes/listar", clientes);
		
		

		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);

		return "listar";
	}

	@GetMapping("/form")
	public String criar(Model model) {
		Cliente cliente = new Cliente();

		model.addAttribute("titulo", "Formulário de Cliente");
		model.addAttribute("cliente", cliente);

		return "form";
	}

	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulário de Cliente");
			return "form";
		}

		clienteService.save(cliente);
		
		status.isComplete();
		
		flash.addFlashAttribute("success", "Cliente salvo com sucesso!");

		return "redirect:listar";
	}
	
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.findById(id);

		model.addAttribute("titulo", "Editando o Cliente");
		model.addAttribute("cliente", cliente);

		return "form";
	}
	
	
	@GetMapping("/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes flash) {
		clienteService.deleteById(id);
		
		flash.addFlashAttribute("success", "Cliente eliminado com sucesso!");

		return "redirect:listar";
	}
	
	
	
	

}
