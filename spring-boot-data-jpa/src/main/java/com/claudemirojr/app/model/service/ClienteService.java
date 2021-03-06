package com.claudemirojr.app.model.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.claudemirojr.app.model.entity.Cliente;
import com.claudemirojr.app.model.entity.Fatura;
import com.claudemirojr.app.model.entity.Produto;

public interface ClienteService {
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);

	public Cliente save(Cliente cliente);
	
	public Cliente findById(Long id);
	
	public void deleteById(Long id);
	
	public List<Produto> findByNomeContaining(String nome);
	
	public void saveFatura(Fatura fatura);
	
	public Produto findProdutoById(Long id);
	
	public Fatura findFaturaById(Long id);
	
	public void deleteFaturaById(Long id);
	
	public Fatura fetchFaturaByIdWithClienteWithItemFaturaWithProduto(Long id);
	
	public Cliente fetchClienteByIdWithFatura(Long id);  

}
