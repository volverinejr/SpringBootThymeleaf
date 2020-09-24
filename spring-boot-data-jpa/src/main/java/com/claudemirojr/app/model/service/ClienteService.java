package com.claudemirojr.app.model.service;

import java.util.List;

import com.claudemirojr.app.model.entity.Cliente;

public interface ClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente save(Cliente cliente);
	
	public Cliente findById(Long id);
	
	public void deleteById(Long id);

}
