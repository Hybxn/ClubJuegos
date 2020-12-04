package com.clubjuegos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Centro;
import com.clubjuegos.repositories.CentroRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Centro
@Service
public class CentroService {

	// Repositorio autocableado.
	@Autowired
	private CentroRepository repository;

	// Añadir un Centro nuevo.
	public Centro add(Centro centro) {
		return repository.save(centro);
	}

	// Listar todos los Centros.
	public List<Centro> findAll() {
		return repository.findAll();
	}

	// Encontrar un Centro por ID o si no existe, devolver null
	public Centro findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Editar un Centro ya existe o guardarlo como nuevo.
	public Centro edit(Centro centro) {
		return repository.save(centro);
	}

	// Borrar un Centro usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un Centro mediante la busqueda de la propia instancia de Centro en el
	// repositorio.
	public void deleteByElement(Centro centro) {
		repository.delete(centro);
	}

}
