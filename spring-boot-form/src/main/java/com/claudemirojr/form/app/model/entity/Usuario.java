package com.claudemirojr.form.app.model.entity;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.claudemirojr.form.app.validator.IdentificadorRegex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	//@Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{2}", message = "{minhavalidacao.identificador}")
	@IdentificadorRegex
	private String identificador;
	
	//@NotEmpty
	private String nome;

	@NotBlank
	private String apelido;

	@NotBlank
	@Size(min = 3, max = 10)
	private String username;

	@NotBlank
	private String password;

	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	
	@Valid
	private Pais pais;
	
	
	@NotEmpty
	private List<String> roles;
	
	
	private boolean habilitar;
	
	@NotBlank
	private String genero;
	

}
