package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Clase para poder usar los ficheros properties creados por el admin en el folder PropetiesAPP
 * @author Pc
 *
 */
@Configuration
@PropertySources({
	@PropertySource("classpath:/PropertiesAPP/titulos.properties")
})
public class TitulosProperties {

}
