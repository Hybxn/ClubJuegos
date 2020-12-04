package com.clubjuegos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Emparejamiento;
import com.clubjuegos.models.Partida;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.repositories.EmparejamientoRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Emparejamiento
@Service
public class EmparejamientoService {

	// Repositorio autocableado de Emparejamientos
	@Autowired
	private EmparejamientoRepository repository;
	// Repositorio autocableado de Partidas
	@Autowired
	private PartidaService partidas;

	// Añadir un nuevo emparejamiento
	public Emparejamiento add(Emparejamiento emparejamiento) {
		return repository.save(emparejamiento);
	}

	// Listar todos los emparejamientos
	public List<Emparejamiento> findAll() {
		return repository.findAll();
	}

	// Encontrar un centro por ID y si no existe, devolver null
	public Emparejamiento findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Editar un centro ya existe o guardarlo como nuevo.
	public Emparejamiento edit(Emparejamiento emparejamiento) {
		return repository.save(emparejamiento);
	}

	// Borrar un centro usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un centro mediante la busqueda del propio centro en el repositorio.
	public void deleteByElement(Emparejamiento emparejamiento) {
		repository.delete(emparejamiento);
	}

	// Listar todos los emparejamientos de una partida en concreto.
	public List<Emparejamiento> findByPartida(int idPartida) {
		return repository.findByPartida(idPartida);
	}

	// Listar todas las partidas en las un Jugador está emparejado.
	public List<Partida> misEmparejamientos(Usuario conectado) {

		// Recogemos todos los emparejamientos
		List<Emparejamiento> todoEmparejamiento = repository.findAll();
		// Creamos una lista de Partidas donde iremos guardando todas las partidas que
		// pasen nuestro filtro.
		List<Partida> partidasEmparejadas = new ArrayList<Partida>();

		// Bucle for each para filtrar todos los emparejamientos
		for (Emparejamiento emparejamiento : todoEmparejamiento) {

			// Condicional para ver que el usuario del emparejamiento es el mismo que el
			// usuario conectado.
			if (emparejamiento.getUsuarioEmparejado().equalsIgnoreCase(conectado.getEmail())) {

				// Condicional que comprueba que el usuario conectado no es el usuario
				// organizador.
				if (!partidas.findById(emparejamiento.getPartidaEmparejada()).getSolicitudPartida()
						.getUsuarioOrganizador().getEmail().equalsIgnoreCase(conectado.getEmail())) {

					// Condicional para comprobar que la partida no ha sido marcada como terminada.
					if (partidas.findById(emparejamiento.getPartidaEmparejada()).getEstadoPartida()
							.getIdEstadoPartida() != 2) {
						// Se añade la partida a la lista tras cumplir todas las condiciones.
						partidasEmparejadas.add(partidas.findById(emparejamiento.getPartidaEmparejada()));
					}
				}
			}
		}
		// Devolvemos el listado de las partidas.
		return partidasEmparejadas;
	}

	// Metodo que se utiliza para eliminar un emparejamiento, es decir, un Jugador
	// abandona la Partida.
	public void abandonarPartida(Usuario conectado, int id) {

		// Lista con todos los emparejamientos
		List<Emparejamiento> emparejamientos = repository.findAll();

		// Bucle for each que recorre todos los emparejamientos
		for (Emparejamiento emparejamiento : emparejamientos) {

			// Condicional para filtrar que la partida del emparejamiento y el usuario
			// conectado coincida antes de eliminarlo.
			if (emparejamiento.getPartidaEmparejada() == id
					&& emparejamiento.getUsuarioEmparejado().equalsIgnoreCase(conectado.getEmail())) {
				// Si cumple la condicion anterior se elimina el emparejamiento.
				repository.deleteById(emparejamiento.getIdEmparejamientos());
			}
		}

	}
}
