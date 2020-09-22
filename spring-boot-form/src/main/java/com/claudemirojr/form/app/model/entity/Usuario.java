package com.claudemirojr.form.app.model.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
	@NotEmpty
	private String username;

	@NotEmpty
	private String password;
	
	@Email
	private String email;

}
