package com.example.demo.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = IdentificadorRexegValidador.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface ValidarLongitud {
	
	
	String message() default "El campo debe tener 3-8 Caracteres (ValidarLongitud.class)";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };


}
