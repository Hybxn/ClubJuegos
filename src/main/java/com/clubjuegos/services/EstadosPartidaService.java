package com.clubjuegos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.EstadosPartida;
import com.clubjuegos.repositories.EstadosPartidaRepository;

//Clase Servicio donde manejaremos los datos del repositorio de EstadosPartida
@Service
public class EstadosPartidaService {

	// Repositorio autocableado.
	@Autowired
	private EstadosPartidaRepository repository;

	//Añadir un EstadosPartida nuevo.
	public EstadosPartida add(EstadosPartida estadospartida) {
		return repository.save(estadospartida);
	}

	//Listar todos los EstadosPartida.
	public List<EstadosPartida> findAll() {
		return repository.findAll();
	}

	//Encontrar un EstadoPartida por ID o si no existe, devolver null
	public EstadosPartida findById(int id) {
		return repository.findById(id).orElse(null);
	}

	//Editar un EstadoPartida ya existe o guardarlo como nuevo.
	public EstadosPartida edit(EstadosPartida estadospartida) {
		return repository.save(estadospartida);
	}

	//Borrar un EstadoPartida usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	//Borrar un EstadosPartida mediante la busqueda de la propia instancia EstadoPartida en el repositorio
	public void deleteByElement(EstadosPartida estadospartida) {
		repository.delete(estadospartida);
	}
}
