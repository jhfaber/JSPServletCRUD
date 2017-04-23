package com.empresa.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.empresa.dao.IPersonaDAO;
import com.empresa.dao.PersonaDAOImpl;
import com.empresa.model.Persona;

@WebServlet("/PersonaController")
public class PersonaController extends HttpServlet {
	
	
	public static final String LIST_PERSONA ="/listaPersona.jsp";
	public static final String INSERT_EDIT = "/persona.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IPersonaDAO dao = new PersonaDAOImpl();		
		String redireccion = "";
		String accion = req.getParameter("accion");
		
		if(accion.equalsIgnoreCase("ELIMINAR")){
			redireccion = LIST_PERSONA;
			int id = Integer.parseInt(req.getParameter("id"));
			dao.eliminar(id);
			req.setAttribute("personas", dao.listarTodos());
		}else if(accion.equalsIgnoreCase("EDITAR")){
			redireccion = INSERT_EDIT;
			int id = Integer.parseInt(req.getParameter("id"));
			Persona persona = dao.listarPorId(id);
			req.setAttribute("persona", persona);
		}else if(accion.equalsIgnoreCase("INSERTAR")){
			redireccion = INSERT_EDIT;
		}else{
			redireccion = LIST_PERSONA;
			req.setAttribute("personas", dao.listarTodos());
		}
		
		RequestDispatcher vista = req.getRequestDispatcher(redireccion);		
		vista.forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Con el metodo POST recibo toda la data de mis formularios. min 52
		IPersonaDAO dao = new PersonaDAOImpl();
		Persona persona = new Persona();
		persona.setNombres(req.getParameter("nombres"));
		persona.setApellidos(req.getParameter("apellidos"));
		String id=req.getParameter("id");
		
		if(id== null || id.isEmpty()){
			dao.agregar(persona);
		}else{
			persona.setId(Integer.parseInt(id));
			dao.actualizar(persona);
		}
		req.setAttribute("personas", dao.listarTodos());
		RequestDispatcher vista = req.getRequestDispatcher(LIST_PERSONA);
		vista.forward(req, resp);
		
	}
	
	
}
