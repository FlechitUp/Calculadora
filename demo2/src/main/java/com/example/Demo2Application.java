package com.example;

import java.sql.Date;

import javax.swing.JOptionPane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Demo2Application {
	
	
	@RequestMapping("/")
	@ResponseBody
	String home(){
		return "Bienvenido!. Realice sus operaciones Opciones: suma, resta, mult y div";
	}
	

	String mensaje_indicaciones(){
		return "Opciones: suma, resta, mult y div";
	}
	
	boolean validar_dato_numerico(String a){
		try{
			Float.parseFloat(a);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
				
	}
	
	boolean validar_operacion(String op){
		return (op.equals("suma") || op.equals("resta") || op.equals("mult") || op.equals("div") )? true  : false;
	}
	
	String division(String a, String b){
		Float a1, b1;
		a1 = Float.parseFloat(a);
		b1 = Float.parseFloat(b);						
		
		if (b.equals("0")) {
		    throw new IllegalArgumentException("____ Argument 'divisor' is 0!!   ");
		}
		else {
			return Float.toString(a1/b1);
		}
		
	} 
	
	String multiplicacion(String a, String b){
		Float a1, b1;
		a1 = Float.parseFloat(a);
		b1 = Float.parseFloat(b);
		return Float.toString(a1*b1);
	} 
	
	
	String resta(String a, String b){
		Float a1, b1;
		a1 = Float.parseFloat(a);
		b1 = Float.parseFloat(b);
		return Float.toString(a1-b1);
	} 
	
	String suma(String a, String b){
		Float a1, b1;
		a1 = Float.parseFloat(a);
		b1 = Float.parseFloat(b);
		return Float.toString(a1+b1);
	} 
	
	@RequestMapping("/calc")
	@ResponseBody
	String calc(@RequestParam String a, String b, String op){
		Boolean validar = true;
		
		validar = validar_dato_numerico(a);
		if(validar){
			validar = validar_dato_numerico(b);
			if (validar){
				validar = validar_operacion(op);
			}
		
		}
		if(validar){
		
			
			String result = "";
			switch(op){
			
			case "suma":
				op = "+";
				result = suma(a,b);
				break;
			case "resta":
				op = "-";
				result = resta(a,b);
				break;
			case "mult":
				op = "*";
				result = multiplicacion(a,b);
				break;
			case "div":
				op = "/";
				result = division(a,b);
				break;											
			}
		
		
			return a + " " + op + " " + b + " = " + result ;
		}
		else{
			return "Inserte los datos correctamente. Ej. /calc?a=5&b=3&op=suma "; 
		}
	}
	

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	
	}
}
