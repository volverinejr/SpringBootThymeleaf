package com.claudemirojr.error.app.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.claudemirojr.error.app.model.entity.Usuario;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private List<Usuario> lista = new ArrayList<>();

	public UsuarioServiceImpl() {
		lista.add(new Usuario(1L, "Claudemiro", "Miro"));
		lista.add(new Usuario(2L, "Frederico", "Fred"));
		lista.add(new Usuario(3L, "Reinaldo", "Rei"));
	}

	@Override
	public List<Usuario> findAll() {
		return lista;
	}

	@Override
	public Usuario findById(Long id) {
		Usuario result = null;

		for (Usuario usuario : lista) {
			if (usuario.getId() == id) {
				result = usuario;
				break;
			}
		}

		return result;
	}

}
