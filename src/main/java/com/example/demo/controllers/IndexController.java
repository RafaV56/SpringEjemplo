package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Fecha;
import com.example.demo.models.Usuario;
import com.example.demo.models.UsuarioValido;




@Controller  //Controladores tienen que ir en un paquete debajo del paquete principal
@RequestMapping("/")//Ruta url para poder acceder a cada url de los metodos
public class IndexController {
	
	/**
	 * Variable que viene desde /PropertiesAPP/titulos.properties
	 */
	@Value("${titulo.pathVariable}")
	private String tituloPath;
	
	/**
	 * Variable que viene desde /PropertiesAPP/titulos.properties
	 */
	@Value("${titulo.properties}")
	private String tituloProperties;

	/**
	 * Variable que viene desde /PropertiesAPP/titulos.properties
	 */
	@Value("${titulo.redirect}")
	private String tituloRedirect;
	
	/**
	 * controlador para la página inicial, con varios url para empezar, se usan las llaves{}
	 * @param name
	 * @param model
	 * @return
	 */
	//@RequestMapping(value = "/index",method = RequestMethod.GET)//tambien se puede usar de está forma, pero es más complicado, method es get por defecto
	@GetMapping({"/index","/","home"})//puedes asignar distintas urls a la mismo controlador
	public String index(@RequestParam(name = "name",required = false,defaultValue = "usuario")String name,Model model) {//se puede usar tambien httpServletRequest para obtener los atributos con el método getParameter("name");
		model.addAttribute("name",name);//Usamos la clase Model para pasar parametros a las vistas. con el método addAtribute
		//Tambien podemos usar la clase ModelMap y funciona igual que model 
		//Map de Java.util tambien puedes usar, como (Map<String,Object> model) y usamos el metodo put de map
		//ModelAndView tambien se puede usar y se le puede asignar la vista, pero es más complejo, necesitas retornar Un ModelAndView en ves de String,y se usa setViewName para asignar la vista
		return "index";
	}
	
	/**
	 * Controlador para listar en una vista un array, se crea y se usa each en la vistar, ver vista listar
	 * @param model
	 * @return
	 */
	@RequestMapping("/listar")
	public String listar(Model model) {
		Usuario usuario=new Usuario();
		Usuario usuario2=new Usuario();
		usuario.setApellido("Velásquez");
		usuario.setNombre("Rafael");
		usuario.setEmail("mail@mimail.com");
		usuario2.setApellido("Perez");
		usuario2.setNombre("Ana");
		List<Usuario> lista=new ArrayList<Usuario>();
		lista.add(usuario);
		lista.add(usuario2);
		model.addAttribute("usuarios",lista);
		
		return "listar";
	}
	
	/**
	 * Controlador para ver un perfil y usar el if en la vista, al preguntar por el email del usuario
	 * @param model
	 * @return
	 */
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario=new Usuario();
		usuario.setApellido("Velásquez");
		usuario.setNombre("Rafael");
		//usuario.setEmail(null);
		model.addAttribute("usuario",usuario);
		return "perfil";
	}
	

	//para enviar variables en el path mucho más limpio
	/**
	 * controlador para usar @PathVariable pasando uno o dos variables
	 * @param variable
	 * @param numero
	 * @param model
	 * @return
	 */
	@GetMapping({"/path/{variable}/{numero}","/path/{variable}"})
	public String path(@PathVariable(name = "variable")String variable, 
			@PathVariable(name = "numero",required = false)Integer numero,
			Model model) {//si el name es el mismo que la variable se puede omitir
		if (numero==null) {
			numero=-1;
		}
		model.addAttribute("titulo",tituloPath);
		model.addAttribute("variable", variable);
		model.addAttribute("numero", numero);
		return "path";
	}
	
	/**
	 * Controlador para ir a la web donde explicas como se usa un fichero .properties creado por el usuario
	 * @param model
	 * @return
	 */
	@GetMapping("/properties")
	public String properties(Model model) {
		
		model.addAttribute("titulo", tituloProperties);
		
		return "properties";
	}
	
	/**
	 * Controlador para ver como usar un redirect y forward
	 * @param model
	 * @return
	 */
	@GetMapping("/redirect")
	public String redirect(Model model) {
		
		model.addAttribute("titulo", tituloRedirect);
		
		return "redirect";
	}
	
	/**
	 * Controlador para ver como usar un redirect 
	 * @param model
	 * @return
	 */
	@GetMapping("/redirectInicio")
	public String redirectFuera(Model model) {
		
		return "redirect:/index";
	}
	/**
	 * Controlador para ver como usar un  forward
	 * @param model
	 * @return
	 */
	@GetMapping("/forwardtInicio")
	public String forward(Model model) {
		
		return "forward:/index";
	}
	
	
	
	
	
	
}
