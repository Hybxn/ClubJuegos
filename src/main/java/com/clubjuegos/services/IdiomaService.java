package com.clubjuegos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Idioma;
import com.clubjuegos.repositories.IdiomaRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Idioma
@Service
public class IdiomaService {

	//Repositorio autocableado.
	@Autowired
	private IdiomaRepository repository;

	//Añadir un Idioma nuevo.
	public Idioma add(Idioma idioma) {
		return repository.save(idioma);
	}

	//Listar todos los Idiomas.
	public List<Idioma> findAll() {
		return repository.findAll();
	}

	//Encontrar un Idioma por ID o si no existe, devolver null
	public Idioma findById(int id) {
		return repository.findById(id).orElse(null);
	}

	//Editar un Idioma ya existe o guardarlo como nuevo.
	public Idioma edit(Idioma idioma) {
		return repository.save(idioma);
	}

	//Borrar un Idioma usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	//Borrar un Idioma mediante la busqueda del propio Idioma en el repositorio.
	public void deleteByElement(Idioma idioma) {
		repository.delete(idioma);
	}
	
	
}
