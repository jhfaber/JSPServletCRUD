package com.empresa.dao;

import java.util.List;

import com.empresa.model.Persona;

public interface IPersonaDAO {
	public void agregar(Persona persona);
	public void eliminar(int PersonaId);
	public void actualizar(Persona persona);
	public List<Persona> listarTodos();
	public Persona listarPorId(int id);
}
