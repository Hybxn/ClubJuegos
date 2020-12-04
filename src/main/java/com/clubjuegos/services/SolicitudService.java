package com.clubjuegos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.EstadosSolicitud;
import com.clubjuegos.models.Solicitud;
import com.clubjuegos.repositories.EstadosSolicitudRepository;
import com.clubjuegos.repositories.SolicitudRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Centro
@Service
public class SolicitudService {

	// Repositorio autocableado.
	@Autowired
	private SolicitudRepository repository;
	// Repositorio autocableado de EstadosSolicitud
	@Autowired
	private EstadosSolicitudRepository estadosrepository;

	// Añadir un Centro nuevo.
	public Solicitud add(Solicitud solicitud) {
		return repository.save(solicitud);
	}

	// Listar todos los Centros.
	public List<Solicitud> findAll() {
		return repository.findAll();
	}

	// Encontrar un Centro por ID o si no existe, devolver null
	public Solicitud findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Editar un Centro ya existe o guardarlo como nuevo.
	public Solicitud edit(Solicitud solicitud) {
		return repository.save(solicitud);
	}

	// Borrar un Centro usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un Centro mediante la busqueda de la propia instancia de Centro en el
	// repositorio.
	public void deleteByElement(Solicitud solicitud) {
		repository.delete(solicitud);
	}

	// Listar todas las solicitudes cuyo EstadosSolicitud sea "Pendiente".
	public List<Solicitud> filtradoPendientes() {
		EstadosSolicitud estado = estadosrepository.findById(1).orElse(null);
		return repository.findByEstadoSolicitudLike(estado);
	}

	// Listar solicitudes de un Centro entre una lista de Juegos de un Centro
	public List<Solicitud> findByCenter(List<Integer> juegos, int idEstado) {
		return repository.findByCenter(juegos, idEstado);
	}

	// Listar las solicitudes de un Usuario en especifico.
	public List<Solicitud> findByUsuario(int idUsuario) {
		return repository.findByUsuario(idUsuario);
	}
}
