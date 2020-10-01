package com.claudemirojr.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.claudemirojr.app.model.entity.Fatura;

public interface FaturaRepository extends JpaRepository<Fatura, Long>{
	
	
	@Query("select f from Fatura f join fetch f.cliente c join fetch f.items l join fetch l.produto where f.id = ?1")
	public Fatura fetchByIdWithClienteWithItemFaturaWithProduto(Long id);

}
