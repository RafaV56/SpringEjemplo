package com.example.demo.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentificadorRexegValidador implements ConstraintValidator<ValidarLongitud, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		return value.length()>=3 && value.length() <=8;
	}

}
