package com.clubjuegos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.EstadosIncidencia;
import com.clubjuegos.repositories.EstadosIncidenciaRepository;

//Clase Servicio donde manejaremos los datos del repositorio de EstadosIncidencia
@Service
public class EstadosIncidenciaService {

	// Repositorio autocableado.
	@Autowired
	private EstadosIncidenciaRepository repository;

	//Añadir un EstadosIncidencia nuevo.
	public EstadosIncidencia add(EstadosIncidencia estadosincidencia) {
		return repository.save(estadosincidencia);
	}

	//Listar todos los EstadosIncidencia.
	public List<EstadosIncidencia> findAll() {
		return repository.findAll();
	}

	//Encontrar un EstadoIncidencia por ID o si no existe, devolver null
	public EstadosIncidencia findById(int id) {
		return repository.findById(id).orElse(null);
	}

	//Editar un EstadoIncidencia ya existe o guardarlo como nuevo.
	public EstadosIncidencia edit(EstadosIncidencia estadosincidencia) {
		return repository.save(estadosincidencia);
	}

	//Borrar un EstadoIncidencia usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	//Borrar un EstadosIncidencia mediante la busqueda de la propia instancia EstadoIncidencia en el repositorio.
	public void deleteByElement(EstadosIncidencia estadosincidencia) {
		repository.delete(estadosincidencia);
	}

}
