package com.example.demo.models;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;


import com.example.demo.validation.ValidarLongitud;

public class UsuarioValido {
	/**
	 * Como validar un número por ejemplo para los años
	 */
	@NotNull
	@Min(1)
	@Max(120)
	private Integer annos;
	
	private Fecha nacimiento;

	public Fecha getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Fecha nacimiento) {
		this.nacimiento = nacimiento;
	}

	/*
	 * usamos en Usuario validador un if para hacerlo allí el size, ya que si lo
	 * hacemos con está anotación primero la ejecuta y despues quita los
	 * espacios(osea va al Usuario validador), y deja pasar la validación con
	 * espacios en blanco o menos caracteres
	 */
	// @Size(max = 8, min = 3)
	private String apellido;

	// @NotEmpty//Valida solo si está vacío pero si tiene espacios en blanco no, es
	// mejor usar @NotBlank
	@NotBlank
	@Email(message = "El email está mal formado") // cuidado con importar mal, es de validation.constraint// con message
													// se puede editar la salida
	private String email;

	private String id;// si no está en el formulario no se válida

	// @NotEmpty
	@NotBlank // Valida solo si está vacío pero si tiene espacios en blanco no, es mejor usar
				// @NotBlank
	// @Size(min = 4, max = 6)//size solo para String, para interger son @Min y @Max
	// @Pattern(regexp = "[a-z]{6}") //se puede validar con expresión regular
	// tambien
	@ValidarLongitud // (message = "mensaje desde la claseee") //Anotacion creada para validar
						// nombres de 3 a 8 caracteres, se puede añadir el mensaje aqui o en
						// mesaages.properties con la clase.obIntanciado.atributo
	private String nombre;

	public Integer getAnnos() {
		return annos;
	}

	public String getApellido() {
		return apellido;
	}

	public String getEmail() {
		return email;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setAnnos(Integer annos) {
		this.annos = annos;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
