package com.example.demo.models;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Clase desarrollada por ejemplosdecodigo.ddns.net el 13/1/2020, para manejar fechas en Espa�ol
 * @author Ejemplos de c�digo (Rafael Antonio Vel�squez Mill�n)
 *
 */
public class Fecha implements Comparable<Fecha>{
	
	/**
	 * Retorna una fecha con su hora
	 * @param dia D�a del mes [1-31]
	 * @param mes Mes del a�o [1 - 12]
	 * @param anno A�o [-999999999 A +999999999]
	 * @param hora hora [0 - 23]
	 * @param minuto Minut [0 - 59]
	 * @return una fecha con su hora
	 */
	private LocalDateTime crearFecha(int dia, int mes, int anno, int hora,int minuto) {
		//Regi�n 1
		String error=null;
		if (dia<1 || dia>31) {
			error="El d�a debe estar en este rango[1-31]";
		//Regi�n 2
		}else if (mes<1 || mes>12) {
			error="El mes debe estar en este rango[1-12]";
		//Regi�n 3
		//Sacamos los datos de LocalDateTime.MAX y LocalDateTime.MIN	
		}else if (anno < -999999999 ||anno>999999999 ) {
			error="El a�o debe estar en este rango[-999999999  A 999999999]";
			//Regi�n 4
		}else if (hora<0 || hora >23) {
			error="La hora debe estar en este rango[0 - 23]";
			//Regi�n 5
		}else if (minuto<0 || minuto>59) {
			error="El  minuto debe estar en este rango[0 - 59]";
		}
		//Regi�n 6
		if (error!=null) {
			throw new RuntimeException(error);
		}
		//Regi�n 7
		LocalDateTime fechasNueva=null;
		try {
			//Si hay errores de fechas como el 30 de Febrero se comprueba 
			fechasNueva=LocalDateTime.of(anno, mes, dia, hora, minuto);
		} catch (DateTimeException e) {
			//Regi�n 8
			//Tomo el valor donde empieza el mensaje de error
			int donde=e.getMessage().indexOf("'");
			//Solo uso del mensaje que devuelve el evento el mes y su valor
			error=e.getMessage().substring(donde);
			if (error.equalsIgnoreCase("'FEBRUARY 31'")) {
				error="El  Mes de Febrero no puede tener 31 d�as";
				//Regi�n 9
			}else if (error.equalsIgnoreCase("'FEBRUARY 30'")) {
				error="El  Mes de Febrero no puede tener 30 d�as";
				//Regi�n 10
			}else if (error.equalsIgnoreCase("'February 29' as '"+anno+"' is not a leap year")) {
				error="El  Mes de Febrero no puede tener 29 d�as si el a�o no es bisiesto";
				//Regi�n 11
			}else if (error.equalsIgnoreCase("'APRIL 31'")) {
				error="El  Mes de Abril no puede tener 31 d�as";
				//Regi�n 12
			}else if (error.equalsIgnoreCase("'JUNE 31'")) {
				error="El  Mes de Junio no puede tener 31 d�as";
				//Regi�n 13
			}else if (error.equalsIgnoreCase("'SEPTEMBER 31'")) {
				error="El  Mes de Septiembre no puede tener 31 d�as";
				//Regi�n 14
			}else if (error.equalsIgnoreCase("'NOVEMBER 31'")) {
				error="El  Mes de Noviembre no puede tener 31 d�as";
			}
		}
		//Regi�n 15
		if (error!=null) {
			throw new RuntimeException(error);
		}
		return fechasNueva;
	}

	/**
	 * Fecha que se usa internamente en la clase
	 */
	private LocalDateTime fecha;

	/**
	 * Crea una fecha con su hora
	  * @param dia D�a del mes [1-31]
	 * @param mes Mes del a�o [1 - 12]
	 * @param anno A�o [-999999999 A +999999999]
	 * @param hora hora [0 - 23]
	 * @param minuto Minuto [0 - 59]
	 */
	public Fecha(int dia, int mes, int anno, int hora,int minuto) {
		super();	
		this.fecha = crearFecha(dia, mes, anno, hora, minuto);
	}
	
	/**
	 * Crea una fecha con la hora 0:0
	 * @param dia D�a del mes [1-31]
	 * @param mes Mes del a�o [1 - 12]
	 * @param anno A�o
	 */
	public Fecha(int dia,int mes, int anno){
		super();
		this.fecha =crearFecha(dia, mes, anno, 0, 0);
	}
	
	/**
	 * Retorna el a�o
	 * @return A�o
	 */
	public int getAnno(){
		return fecha.getYear();
	}
	
	/**
	 * Retorna el d�a del a�o [1- 365 o 366 si es bisiesto]
	 * @return D�a del a�o
	 */
	public int getDiaDelAnno(){
		return fecha.getDayOfYear();
	}
	
	/**
	 * Retorna el d�a de la semana en espa�ol y en may�sculas
	 * @return D�a de la semana
	 */
	public String getDiaDeLaSemana(){
		DayOfWeek diaDelAnno=fecha.getDayOfWeek();
		
		return diaDelAnno.getDisplayName(TextStyle.FULL, new Locale("es")).toUpperCase();
	}

	/**
	 * Retorna el d�a del mes
	 * @return D�a del mes
	 */
	public int getDiaDelmes(){
		return fecha.getDayOfMonth();
	}

	/**
	 * Retorna la hora [0 - 23]
	 * @return hora
	 */
	public int getHora(){
		return fecha.getHour();
	}
	
	/**
	 * Retorna el mes en Espa�ol y en may�sculas
	 * @return Mes
	 */
	public String getMes() {
	
		Month mes=fecha.getMonth();
		return mes.getDisplayName(TextStyle.FULL, new Locale("es")).toUpperCase();
	}
	/**
	 * Retorna el mes en un valor n�merico [1-12]
	 * @return Mes en valor n�merico
	 */
	public int getMesEnNumero() {
		return fecha.getMonthValue();
	}

	/**
	 * Retorna el minuto de la hora	
	 * @return Minutos
	 */
	public int getMinuto() {
		return fecha.getMinute();
	}
	
	/**
	 * Retorna una fecha sumando los d�as pasados como parametro, tienen que ser positivos
	 * @param dias D�as para sumarle a la fecha (Solo Positivos)
	 * @return Fecha nueva con la suma de d�as
	 */
	public Fecha sumarDias(long dias){
		LocalDateTime nueva=null;
		if (dias<=0) {
			throw new RuntimeException("Los d�as a sumar tienen que ser superior a cero");
		}else{
		 try {
			nueva=fecha.plusDays(dias);
		} catch (DateTimeException e) {
			throw new RuntimeException("Los d�as a suma estan fuera del rango permitido");
		}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
		
	}

	/**
	 * Retorna la fecha restando los d�as pasado por parametros, o, tienen que ser positivos
	 * @param dias D�as que se quiere restar (Solo Positivos)
	 * @return Fecha con la resta de d�as
	 */
	public Fecha restarDias(long dias){
		LocalDateTime nueva=LocalDateTime.now();
		if (dias<=0) {
			throw new RuntimeException("Los d�as a restar tienen que ser superior a cero");
		}else{
		 try {
			nueva=fecha.minusDays(dias);
		} catch (DateTimeException e) {
			throw new RuntimeException("Los d�as a restar estan fuera del rango permitido");
		}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}
	
	/**
	 * Retorna una Fecha sumando las semanas que pasadas por parametro
	 * @param semanas Las semanas a sumar a tu fecha
	 * @return Fecha nueva con las semanas sumadas
	 */
	public Fecha sumarSemanas(long semanas){
		LocalDateTime nueva=LocalDateTime.now();
		if (semanas <= 0) {
			throw new RuntimeException("Las semanas a sumar tienen que ser una o m�s");
		}else{
			 try {
				nueva=fecha.plusWeeks(semanas);
			} catch (DateTimeException e) {
				throw new RuntimeException("Las semanas a sumar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}
	
	/**
	 * Retorna un fecha con la resta de las semanas pasadas por parametro
	 * @param semanas Semanas a restar a la fecha
	 * @return Fecha nueva con las semanas restadas
	 */
	public Fecha restarSemanas(long semanas){
		LocalDateTime nueva=LocalDateTime.now();
		if (semanas <= 0) {
			throw new RuntimeException("Las semanas a restar tienen que ser una o m�s");
		}else{
			 try {
				nueva=fecha.minusWeeks(semanas);
			} catch (DateTimeException e) {
				throw new RuntimeException("Las semanas a restar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}
	
	/**
	 * Retorna un fecha con la suma de los a�os pasados por parametro
	 * @param annos A�os a sumar 1 o m�s
	 * @return Fecha nueva con los a�os sumados
	 */
	public Fecha sumarAnnos(long annos) {
		LocalDateTime nueva=LocalDateTime.now();
		if (annos <= 0) {
			throw new RuntimeException("Los a�os a sumar tienen que ser uno o m�s");
		}else{
			 try {
				nueva=fecha.plusYears(annos);
			} catch (DateTimeException e) {
				throw new RuntimeException("Los a�os a sumar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}
	/**
	 *  Retorna un fecha con la resta de los a�os pasados por parametro
	 * @param annos A�os a restar 1 o m�s
	 * @return Fecha nueva con los a�os restados
	 */
	public Fecha restarAnnos(long annos) {
		LocalDateTime nueva=LocalDateTime.now();
		if (annos <= 0) {
			throw new RuntimeException("Los a�os a restar tienen que ser uno o m�s");
		}else{
			 try {
				nueva=fecha.minusYears(annos);
			} catch (DateTimeException e) {
				throw new RuntimeException("Los a�os a restar estan fuera del rango permitido");
			}
		}
		return new Fecha(nueva.getDayOfMonth(), nueva.getMonthValue(), nueva.getYear(), nueva.getHour(), nueva.getMinute());
	}
	
	/**
	 * retorna la fecha del sistema
	 * @return fecha nueva del sistema
	 */
	public static Fecha creaFechaDeAhora(){
		return new Fecha(LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear(), LocalTime.now().getHour(),LocalTime.now().getMinute() );
	}
	
	/**
	 * Retorna un String con solo la fecha en la zona que estas, ej: 16 de enero de 2020
	 * @return
	 */
	private String verFechaLarga(){
		return fecha.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
	}
	
	
	/**
	 * Calcula los a�os, meses y d�as han pasado hasta la fecha que se pasa por parametro
	 * @param nueva Fecha que se quiere compara
	 * @return
	 */
	public Period calcularAnno(Fecha nueva){
		LocalDate restar=LocalDate.of(nueva.getAnno(), getMesEnNumero(), nueva.getDiaDelmes());
		LocalDate mia=LocalDate.of(fecha.getYear(), fecha.getMonthValue(), fecha.getDayOfMonth());
		
		Period periodo=Period.between(mia, restar);
		return periodo;
	}

	/**
	 * Obtienes muchos datos sobre la  fecha
	 * @return Datos de la fecha en espa�ol
	 */
	public String getDatosDeLaFecha() {
		return String.format("\n%s\n\tD�a del a�o: %d\n\tD�a de la semana: %s"
				+ "\n\tHora : %d Minuto : %d\n\tMes en n�mero: %d", verFechaLarga(),getDiaDelAnno(),getDiaDeLaSemana(),getHora(),getMinuto(),getMesEnNumero());
	}

	@Override
	public boolean equals(Object obj) {
		Fecha comparar=(Fecha)obj;
		boolean bandera=false;
		if (comparar==null) {
			throw new RuntimeException("La fecha a comparar es nula");
		}else if (comparar.getAnno()!=getAnno()) {
			bandera=false;
		}else if (comparar.getMesEnNumero()!=getMesEnNumero()) {
			bandera=false;
		}else if (comparar.getDiaDelmes()!=getDiaDelmes()) {
			bandera=false;
		}else if (comparar.getHora()!=getHora()) {
			bandera=false;
		}else if (comparar.getMinuto()!=getMinuto()) {
			bandera=false;
		}else {
			bandera=true;
		}
	return bandera;
	}

	@Override
	public String toString() {
		return ""+verFechaLarga()+ " "+getHora()+":"+getMinuto()+"";
	}

	@Override
	public int compareTo(Fecha obj) {
		int retorna=0;
		if (obj.getAnno()>getAnno()) {
			retorna=1;
		}else if (obj.getAnno()<getAnno()) {
			retorna=-1;
		}else if (obj.getMesEnNumero()>getMesEnNumero()) {
			retorna=1;
		}else if (obj.getMesEnNumero()<getMesEnNumero()) {
			retorna=-1;
		}else if (obj.getDiaDelmes()>getDiaDelmes()) {
			retorna=1;
		}else if (obj.getDiaDelmes()<getDiaDelmes()) {
			retorna=-1;
		}else if (obj.getHora()>getHora()) {
			retorna=1;
		}else if (obj.getHora()<getHora()) {
			retorna=-1;
		}else if (obj.getMinuto()>getMinuto()) {
			retorna=1;
		}else if (obj.getMinuto()<getMinuto()) {
			retorna=-1;
		}else {
			retorna=0;
		}
	
		return retorna;
	}


}
