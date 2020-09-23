package com.claudemirojr.error.app.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	private Long id;
	private String nome;
	private String apelido;

}
