package com.claudemirojr.app.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claudemirojr.app.model.entity.Cliente;
import com.claudemirojr.app.model.entity.Fatura;
import com.claudemirojr.app.model.entity.Produto;
import com.claudemirojr.app.model.repository.ClienteRepository;
import com.claudemirojr.app.model.repository.FaturaRepository;
import com.claudemirojr.app.model.repository.ProdutoRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FaturaRepository faturaRepository;
	
	
	

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Produto> findByNomeContaining(String nome) {
		return produtoRepository.findByNomeContaining(nome);
	}

	@Override
	@Transactional
	public void saveFatura(Fatura fatura) {
		faturaRepository.save(fatura);
	}

	@Override
	@Transactional(readOnly = true)
	public Produto findProdutoById(Long id) {
		return produtoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Fatura findFaturaById(Long id) {
		return faturaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteFaturaById(Long id) {
		faturaRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Fatura fetchFaturaByIdWithClienteWithItemFaturaWithProduto(Long id) {
		return faturaRepository.fetchByIdWithClienteWithItemFaturaWithProduto(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente fetchClienteByIdWithFatura(Long id) {
		return clienteRepository.fetchByIdWithFatura(id);
	}


}
