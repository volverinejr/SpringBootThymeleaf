package com.claudemirojr.form.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificadorRegexValidador implements ConstraintValidator<IdentificadorRegex, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value.matches("[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][A-Z]{2}")) {
			return true;
		}

		return false;
	}

}
