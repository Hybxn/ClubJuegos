package com.clubjuegos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Rol;
import com.clubjuegos.repositories.RolRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Rol
@Service
public class RolService {

	// Repositorio autocableado.
	@Autowired
	private RolRepository repository;

	// Añadir un Rol nuevo.
	public Rol add(Rol rol) {
		return repository.save(rol);
	}

	// Listar todos los Rols.
	public List<Rol> findAll() {
		return repository.findAll();
	}

	// Encontrar un Rol por ID o si no existe, devolver null
	public Rol findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Editar un Rol ya existe o guardarlo como nuevo.
	public Rol edit(Rol rol) {
		return repository.save(rol);
	}

	// Borrar un Rol usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un Rol mediante la busqueda de la propia instancia de Rol en el
	// repositorio.
	public void deleteByElement(Rol rol) {
		repository.delete(rol);
	}
}
