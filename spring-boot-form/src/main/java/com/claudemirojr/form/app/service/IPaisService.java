package com.claudemirojr.form.app.service;

import java.util.List;

import com.claudemirojr.form.app.model.entity.Pais;

public interface IPaisService {

	public List<Pais> listar();
	
	public Pais findBiId(Integer id);
	
}
