package com.claudemirojr.error.app.controller.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.claudemirojr.error.app.exception.UsuarioNaoEncontradoException;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(ArithmeticException.class)
	public String aritmeticaError(Exception ex, Model model) {

		model.addAttribute("error", "Erro de Aritimética");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/aritimetica";
	}
	
	
	
	
	@ExceptionHandler(NumberFormatException.class)
	public String numberFormatError(NumberFormatException ex, Model model) {

		model.addAttribute("error", "Erro de Conversão");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/number";
	}
		
	
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public String usuarioNaoEncontradoException(UsuarioNaoEncontradoException ex, Model model) {

		model.addAttribute("error", "Usuário não encontrado");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status", HttpStatus.NOT_FOUND.value());
		model.addAttribute("timestamp", new Date());
		
		return "error/number";
	}
		
	

}
