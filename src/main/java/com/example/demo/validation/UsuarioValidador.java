package com.example.demo.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.demo.models.UsuarioValido;

/**
 * Clase para validar un usuarioValido, en este caso validamo el apellido que no tenga, 
 * Espacios en blanco y que su tamaño sea entre 3-8 caracteres, tambien que no tenga 
 * espacios en blanco al final, quit/andolos
 * @author ejemplosdecodigo.ddns.org 18/3/2020
 *
 */
@Component// importante usar la anotacion para inyectar
public class UsuarioValidador implements Validator{//imporatar la de springframework.validation

	/**
	 * Este método sirve solo para devolver la clase a validar
	 */
	@Override
	public boolean supports(Class<?> clazz) {
	
		//Si la clase usuario es asignable a la clase que se pasa por argumento
		return UsuarioValido.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UsuarioValido usuario=(UsuarioValido)target;
		//primero validados si el apellido no está vacío
		//ValidationUtils.rejectIfEmpty(errors, "apellido", "NotEmpty.usuarioValido.apellido");
		
		//revisamos que no tenga espacios al final y los quitamos
		revisarEspaciosEnBlancoAlFinal(usuario);
		
		//Luego revisamos que tenga el tamaño de 3 - 8 caracteres
		if (usuario.getApellido().length()<3 || usuario.getApellido().length()>8 ) {
			errors.rejectValue("apellido", "size.usuarioValido.apellido");
		}
		
		
		 //Tambien se puede usar de la siguiente forma y sería igual que arriba pero se puede validar que no termine con espacios en blanco
		if (usuario.getApellido().trim().isEmpty()) {
			errors.rejectValue("apellido", "NotEmpty.usuarioValido.apellido");
		}
		
		/*
		 * se puede tambien usar una expresión regular con el método matches
		
		if (!usuario.getApellido().matches("[a-z]{6}")) {
			errors.rejectValue("apellido", "NotEmpty.usuarioValido.apellido");
		}
		 */
		
		
		
	}

	/**
	 * Revisa los espacios en blanco al final de un string para borrarlos
	 * @param usu usuario a validar
	 */
	private void revisarEspaciosEnBlancoAlFinal(UsuarioValido usu) {
		//Revisamos que no termine en espacios en blanco
		boolean bandera=false; //si se encuentra un caracter diferente a espacio ya no se válida más
		String apoyo="";
		int largo=usu.getApellido().length()-1;
		//Revisamos los espacios finales
		for (int i =largo; i >= 0; i--) {
			//si todavia no hay bander
			if (!bandera && usu.getApellido().charAt(i)==' ') {
				continue;
			}else {
				bandera=true;
				apoyo=usu.getApellido().charAt(i)+apoyo;
			}
			
		}
		
		usu.setApellido(apoyo.toString().trim());
		
		
	
		
	}

}
