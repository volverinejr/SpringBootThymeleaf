package com.claudemirojr.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudemirojr.app.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

}
