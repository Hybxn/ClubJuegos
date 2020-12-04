package com.clubjuegos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Juego;
import com.clubjuegos.repositories.JuegoRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Juego
@Service
public class JuegoService {

	// Repositorio autocableado.
	@Autowired
	private JuegoRepository repository;

	// Añadir un Juego nuevo.
	public Juego add(Juego juego) {
		return repository.save(juego);
	}

	// Listar todos los Juegos.
	public List<Juego> findAll() {
		return repository.findAll();
	}

	// Encontrar un Juego por ID o si no existe, devolver null
	public Juego findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Editar un Juego ya existe o guardarlo como nuevo.
	public Juego edit(Juego juego) {
		return repository.save(juego);
	}

	// Borrar un Juego usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un Juego mediante la busqueda de la propia instancia de Juego en el
	// repositorio.
	public void deleteByElement(Juego juego) {
		repository.delete(juego);
	}

	//Lista de Juegos de un Centro
	public List<Juego> findByCentro(int idCentro) {
		return repository.findByCentro(idCentro);
	}

	//Lista de IDs de juegos del un Centro
	public List<Integer> findCodigoByCentro(int idCentro) {
		return repository.findCodigoByCentro(idCentro);
	}

}
