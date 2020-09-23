package com.claudemirojr.form.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.claudemirojr.form.app.model.entity.Pais;

@Service
public class PaisServiceImpl implements IPaisService {

	private List<Pais> paises = new ArrayList<>();

	public PaisServiceImpl() {
		paises.add(new Pais(1, "BR", "Brasil"));
		paises.add(new Pais(2, "MX", "México"));
		paises.add(new Pais(3, "CL", "Chile"));
	}

	@Override
	public List<Pais> listar() {
		return paises;
	}

	@Override
	public Pais findBiId(Integer id) {
		Pais result = null;

		for (Pais pais : paises) {
			if (pais.getId() == id) {
				result = pais;
				break;
			}
		}

		return result;
	}

}
