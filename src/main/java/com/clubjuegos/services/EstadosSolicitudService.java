package com.clubjuegos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.EstadosSolicitud;
import com.clubjuegos.repositories.EstadosSolicitudRepository;

//Clase Servicio donde manejaremos los datos del repositorio de EstadosSolicitud
@Service
public class EstadosSolicitudService {

	// Repositorio autocableado.
	@Autowired
	private EstadosSolicitudRepository repository;

	//Añadir un EstadosSolicitud nuevo.
	public EstadosSolicitud add(EstadosSolicitud estadossolicitud) {
		return repository.save(estadossolicitud);
	}

	//Listar todos los EstadosSolicitud.
	public List<EstadosSolicitud> findAll() {
		return repository.findAll();
	}

	//Encontrar un EstadoSolicitud por ID o si no existe, devolver null
	public EstadosSolicitud findById(int id) {
		return repository.findById(id).orElse(null);
	}

	//Editar un EstadoSolicitud ya existe o guardarlo como nuevo.
	public EstadosSolicitud edit(EstadosSolicitud estadossolicitud) {
		return repository.save(estadossolicitud);
	}

	//Borrar un EstadoSolicitud usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	//Borrar un EstadosSolicitud mediante la busqueda de la propia instancia EstadoSolicitud en el repositorio.
	public void deleteByElement(EstadosSolicitud estadossolicitud) {
		repository.delete(estadossolicitud);
	}

}
