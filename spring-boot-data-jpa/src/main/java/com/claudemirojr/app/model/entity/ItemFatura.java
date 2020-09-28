package com.claudemirojr.app.model.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "faturas_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemFatura implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer quantidade;

	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;
	
	
	
	public Double calcularTotal() {
		return this.quantidade.doubleValue() * produto.getPreco();
	}

}
