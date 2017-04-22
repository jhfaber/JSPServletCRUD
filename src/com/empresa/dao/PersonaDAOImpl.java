package com.empresa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.empresa.model.Persona;

public class PersonaDAOImpl implements IPersonaDAO{
	
	private Connection cx;
	public PersonaDAOImpl(){
		cx = Conexion.conectar();
	
	}
	@Override
	public void agregar(Persona persona) {
		try{
			String sql="INSERT INTO PERSONA(nombres, apellidos) VALUES(?,?)";
			
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			preparedStatement.setString(1,persona.getNombres());
			preparedStatement.setString(2,persona.getApellidos());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void eliminar(int id) {
		try {
			String sql = "DELETE FROM PERSONA WHERE ID = ?";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizar(Persona persona) {
		try {
			String sql = "UPDATE PERSONA SET NOMBRES= ?, APELLIDOS= ? WHERE ID = ? ";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			preparedStatement.setString(1, persona.getNombres());
			preparedStatement.setString(2, persona.getApellidos());
			preparedStatement.setInt(3, persona.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
				
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Persona> listarTodos() {
		List<Persona> personas = new ArrayList<>();
		try{
			Statement statement = cx.createStatement();
			ResultSet resulSet = statement.executeQuery("Select * FROM persona" );
			while(resulSet.next()){
				Persona persona = new Persona(); 
			    persona.setId(resulSet.getInt("id"));
			    persona.setNombres(resulSet.getString("nombres"));
			    persona.setApellidos(resulSet.getString("apellidos"));
			    personas.add(persona);
			}
			resulSet.close();
			statement.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return personas;
	}

	@Override
	public Persona listarPorId(int id) {
		Persona persona = new Persona();
		try{
			String query =" SELECT * FROM persona WHERE ID=?";
			PreparedStatement preparedStatement = cx.prepareStatement(query);
			preparedStatement.setInt(1,id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				persona.setId(resultSet.getInt("id"));
				persona.setNombres(resultSet.getString("nombres"));
				persona.setApellidos(resultSet.getString("apellidos"));				
			}
			resultSet.close();
			preparedStatement.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return persona;
	}
	
}
