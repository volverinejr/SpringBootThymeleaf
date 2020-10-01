package com.claudemirojr.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.claudemirojr.app.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	@Query("select c from Cliente c left join fetch c.faturas where c.id = ?1")
	public Cliente fetchByIdWithFatura(Long id);

}
