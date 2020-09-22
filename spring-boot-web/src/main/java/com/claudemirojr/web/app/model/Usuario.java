package com.claudemirojr.web.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	private String nome;
	private String apelido;
	private String email;
	
}
