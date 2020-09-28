package com.claudemirojr.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claudemirojr.app.model.entity.Cliente;
import com.claudemirojr.app.model.entity.Fatura;
import com.claudemirojr.app.model.service.ClienteService;

@Controller
@RequestMapping("/fatura")
@SessionAttributes("fatura")
public class FaturaController {
	
	@Autowired
	private ClienteService clienteService;
	

	@GetMapping("/form/{clienteId}")
	public String Criar(@PathVariable Long clienteId, Model model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findById(clienteId);
		
		if (cliente == null) {
			flash.addFlashAttribute("error", "Cliente não existe com o Id: " + clienteId.toString() );
			return "redirect:listar";
		}
		
		
		Fatura fatura = new Fatura();
		
		fatura.setCliente(cliente);
		
		
		model.addAttribute("fatura", fatura);
		model.addAttribute("titulo", "Criar Fatura");
		
		
		return "fatura/form";
	}

}
