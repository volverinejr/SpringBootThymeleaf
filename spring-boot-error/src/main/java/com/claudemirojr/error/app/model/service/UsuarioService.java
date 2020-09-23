package com.claudemirojr.error.app.model.service;

import java.util.List;

import com.claudemirojr.error.app.model.entity.Usuario;

public interface UsuarioService {
	
	
	public List<Usuario> findAll();
	
	public Usuario findById(Long id);

}
