package com.claudemirojr.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.claudemirojr.app.model.entity.Cliente;
import com.claudemirojr.app.model.entity.Fatura;
import com.claudemirojr.app.model.entity.ItemFatura;
import com.claudemirojr.app.model.entity.Produto;
import com.claudemirojr.app.model.service.ClienteService;

@Controller
@RequestMapping("/fatura")
@SessionAttributes("fatura")
public class FaturaController {

	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {
		//Fatura fatura = clienteService.findFaturaById(id);
		Fatura fatura = clienteService.fetchFaturaByIdWithClienteWithItemFaturaWithProduto(id);
		
		
		if (fatura == null) {
			flash.addFlashAttribute("error", "Fatura não existe na base de dados!");
			
			return "redirect:/clientes/listar";
		}

		model.addAttribute("titulo", "Fatura: " + fatura.getDescricao() );
		model.addAttribute("fatura", fatura);
		
		return "fatura/ver";
	}	
	
	
	

	@GetMapping("/form/{clienteId}")
	public String Criar(@PathVariable Long clienteId, Model model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findById(clienteId);

		if (cliente == null) {
			flash.addFlashAttribute("error", "Cliente não existe com o Id: " + clienteId.toString());
			return "redirect:listar";
		}

		Fatura fatura = new Fatura();

		fatura.setCliente(cliente);

		model.addAttribute("fatura", fatura);
		model.addAttribute("titulo", "Criar Fatura");

		return "fatura/form";
	}

	@GetMapping(value = "/carregar-produtos/{term}", produces = { "application/json" })
	public @ResponseBody List<Produto> carregarProdutos(@PathVariable String term) {
		return clienteService.findByNomeContaining(term);
	}
	
	
	@PostMapping("/form")
	public String guardar(@Valid Fatura fatura, BindingResult result, 
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "quantidade[]", required = false) Integer[] quantidade,
			Model model, 
			RedirectAttributes flash, 
			SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Criar Fatura");
			return "fatura/form";
		}
		
		if (itemId == null || itemId.length == 0 ) {
			model.addAttribute("titulo", "Criar Fatura");
			flash.addAttribute("error", "A fatura está vazia!");
			return "fatura/form";
		}
		
		
		
		
		for (int i = 0; i < itemId.length; i++) {
			Produto produto = clienteService.findProdutoById(itemId[i]);
			
			ItemFatura linha = new ItemFatura();
			
			linha.setProduto(produto);
			linha.setQuantidade(quantidade[i]);
			
			
			fatura.addItemFatura(linha);
		}
		
		clienteService.saveFatura(fatura);
		
		status.isComplete();

		flash.addFlashAttribute("success", "Fatura criada com sucesso!");
		
		
		return "redirect:/clientes/ver/" + fatura.getCliente().getId();
	}
	
	
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		Fatura fatura= clienteService.findFaturaById(id);
		
		if( fatura != null) {
			clienteService.deleteFaturaById(id);
			flash.addFlashAttribute("success", "Fatura removida com sucesso!");
			return "redirect:/clientes/ver/" + fatura.getCliente().getId();
		}
		flash.addFlashAttribute("error", "Fatura não existe na base de dados");
		
		return "redirect:/listar";
	}	

}
