package com.claudemirojr.form.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.claudemirojr.form.app.model.entity.Usuario;

@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//Usuario usuario = (Usuario) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty");

		/*
		 * if ( ! usuario.getIdentificador().matches(
		 * "[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{2}")) {
		 * errors.rejectValue("identificador", "pattern.identificador"); }
		 */

	}

}
