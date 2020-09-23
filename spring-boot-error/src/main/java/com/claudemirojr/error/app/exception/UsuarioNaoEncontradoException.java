package com.claudemirojr.error.app.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioNaoEncontradoException(String id) {
		super("Usuário com id: ".concat(id).concat(" não encontrado"));
		
	}
	

}
