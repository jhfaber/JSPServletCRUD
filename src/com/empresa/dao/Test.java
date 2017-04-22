package com.empresa.dao;

import java.util.List;

import com.empresa.model.Persona;

public class Test {
	public static void main(String[] args) {
		IPersonaDAO dao = new PersonaDAOImpl();
		Persona per = new Persona();
		per.setNombres("Juan");
		per.setApellidos("Marin");
		//dao.agregar(per);
		//Lambda
		List<Persona> lista = dao.listarTodos();
		//lista.forEach(x -> System.out.println(x.getId()+ "-"+x.getNombres() +x.getApellidos()));
		
		for(Persona x: lista){
			System.out.println(x.getId()+ "-"+x.getNombres() +x.getApellidos());
		}
	}
}
