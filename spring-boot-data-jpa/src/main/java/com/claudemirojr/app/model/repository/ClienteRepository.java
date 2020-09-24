package com.claudemirojr.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudemirojr.app.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
