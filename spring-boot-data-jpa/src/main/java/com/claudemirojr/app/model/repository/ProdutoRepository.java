package com.claudemirojr.app.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudemirojr.app.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	public List<Produto> findByNomeContaining(String nome);

}
