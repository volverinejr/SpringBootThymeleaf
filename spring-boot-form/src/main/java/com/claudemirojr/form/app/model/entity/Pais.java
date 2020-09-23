package com.claudemirojr.form.app.model.entity;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pais {

	@NotNull
	private Integer id;
	
	private String codigo;
	
	private String nome;
	
}
