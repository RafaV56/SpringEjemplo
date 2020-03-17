package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Fecha;
import com.example.demo.models.Usuario;
import com.example.demo.models.UsuarioValido;

@Controller
public class FormController {
	
	/**
	 * Variable tomada del fichero desde /PropertiesAPP/titulos.properties
	 */
	@Value("${titulo.formulario}")
	private String tituloForm;
	
	/**
	 * Formalarios get
	 * @param model
	 * @return
	 */
	@GetMapping("/form")
	public String form(Model model) {
		return "form";
	}
	/**
	 * Formalarios post
	 * @param model
	 * @return
	 */
	@PostMapping("/form")
	public String formPost(Model model,
			@RequestParam String username, //el nombre de la variable debe ser igual a la del name del form 
			@RequestParam(value = "password") String password, //puedes usar value tambien
			@RequestParam String email) {
		
		//verificamos que no esten vacios para que no se muestren las tarjetas rojas en la vista
		username=username.isEmpty()?null:username;
		password=password.isEmpty()?null:password;
		email=email.isEmpty()?null:email;

		model.addAttribute("username", username); //pasamos las variables al modelo
		model.addAttribute("password", password);
		model.addAttribute("email", email);
		return "form";
	}
	
	/**
	 * handler para usar con un usuario directamente y no usar un @RequestParam,
	 * El propio Spring toma los nombre de los inputs del formulario y los añade
	 * a la variable que esta de primera, como usuario, respetando el nombre de los 
	 * seterrs de la clase usurio con los name de los inputs del formulario, así solo se
	 * añade al atributo del model
	 * @param usuario
	 * @param model
	 * @return
	 */
	@PostMapping("/formClass")
	public String formPostClase(Usuario usuario, Model model) {
		// Se pone nulo si estan vaciós para que no se muestren los bloques rojos -----
		usuario.setApellido(usuario.getApellido().isEmpty()?null:usuario.getApellido());
		usuario.setNombre(usuario.getNombre().isEmpty()?null:usuario.getNombre());
		usuario.setEmail(usuario.getEmail().isEmpty()?null:usuario.getEmail());
		//------------------------------------------------------------------------------
		model.addAttribute("usuario", usuario);
		return "formClass";
	}
	
	/**
	 * Mostrar la vista del fomuralio donde se usa una clase para recibir
	 * @param model
	 * @return
	 */
	@GetMapping("/formClass")
	public String formClas(Model model) {
		return "formClass";
	}
	
	
	/**
	 * Muestra la vista de un formulario donde se usa @valid y un Map para los errores, el propio usuario lo inyecta al modelo que va a la vista y se puede usar
	 * @param model
	 * @return
	 */
	@PostMapping("/formClassMap")//Se puede usar @ModelAttribute("user") para cambiar el nombre de la variable en la vista
	public String formPostMap(@Valid UsuarioValido usuariovalido, BindingResult result, Model model) {//Binding despues del objeto a validar, y el obj el primero
		
		if (result.hasErrors()) {//Mirarmos si hay errores
			Map<String,String> errores=new HashMap<>();//Creamos un map para insertar los errores
			result.getFieldErrors().forEach(err ->{//Recogemos la listar de errores y usamos foreach para hacer una funcion lambda y añadir cada error
				errores.put(err.getField(), "El campo "+err.getField()+" "+err.getDefaultMessage());
			});
			model.addAttribute("error",errores); //añadimos los errores como una variable al model
			return "formClassMap";
		}
		return "formClassMap";
	}
	
	
	/**
	 * Muestra la vista de un formulario donde se usa @valid y un Map para los errores
	 * @param model
	 * @return
	 */
	@GetMapping("/formClassMap")
	public String formClassMap(Model model) {
		model.addAttribute("usuarioValido", new UsuarioValido());//Se envía un usuario para que no tenga errores la vista
		
		return "formClassMap";
	}
	
	
	/**
	 * Muestra el la vista para el uso de etiquetas thymeleaf de formularios
	 * @param model
	 * @return
	 */
	@GetMapping("/formClassThymeleaf")
	public String formThymeleaf(Model model) {
		UsuarioValido usuarioValido= new UsuarioValido();
		usuarioValido.setApellido("Pasando este dato desde el controlador");
		model.addAttribute("usuarioValido", usuarioValido);//Se envía un usuario para que no tenga errores la vista
		
		return "formClassThymeleaf";
	}
	
	
	/**
	 * Uso de thyme leaf para solucionar los errores y el codigo de formularios, y pasar lo errores más sencillo 
	 * @param usuariovalido
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/formClassThymeleaf")//Se puede usar @ModelAttribute("user") para cambiar el nombre de la variable en la vista
	public String formPostThymeleaf(@Valid UsuarioValido usuariovalido,
			BindingResult result,
			Model model) {//Binding despues del objeto a validar, y el obj el primero
		//aqúi ya podemos olvidar enviar erroes y al usuario
		return "formClassThymeleaf";
	}
	
	
	/**
	 * Este método es un atributo que siempre llevará el Modelo, algo común para todos como la fecha
	 * @return
	 */
	@ModelAttribute("titulo")//Atributo que siempre llevara el model
	public String titulo() {
		return tituloForm;
	}
	/**
	 * Este método es un atributo que siempre llevará el Modelo, algo común para todos como la fecha
	 * @return
	 */
	@ModelAttribute("fecha")//Atributo que siempre llevara el model
	public Fecha fecha() {
		Fecha fecha=Fecha.creaFechaDeAhora();
		return fecha;
	}
	

}
