package com.clubjuegos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Emparejamiento;
import com.clubjuegos.models.Partida;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.repositories.PartidaRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Partida
@Service
public class PartidaService {

	// Repositorio autocableado.
	@Autowired
	private PartidaRepository repository;
	// Servicio autocableado de Emparejamiento.
	@Autowired
	private EmparejamientoService emparejamientos;

	// Añadir un Partida nuevo.
	public Partida add(Partida partida) {
		return repository.save(partida);
	}

	// Listar todos los Partidas.
	public List<Partida> findAll() {
		return repository.findAll();
	}

	// Encontrar un Partida por ID o si no existe, devolver null
	public Partida findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Editar un Partida ya existe o guardarlo como nuevo.
	public Partida edit(Partida partida) {
		return repository.save(partida);
	}

	// Borrar un Partida usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un Partida mediante la busqueda de la propia instancia de Partida en
	// el
	// repositorio.
	public void deleteByElement(Partida partida) {
		repository.delete(partida);
	}

	// Metodo que devuelve la lista de partidas a las que un usuario puede unirse.
	public List<Partida> buscarPartida(Usuario conectado) {

		// Lista con todas las partidas.
		List<Partida> todaPartida = repository.findAll();
		// Lista para almacenar las partidas filtradas.
		List<Partida> filtro = new ArrayList<Partida>();

		// Bucle foreach para recorrer todas las partidas.
		for (Partida partida : todaPartida) {

			// Condicional para descartar las Partidas en las que el Usuario Organizador es
			// el Usuario conectado.
			if (partida.getSolicitudPartida().getUsuarioOrganizador().getIdUsuario() != conectado.getIdUsuario()) {

				// Condicional para asegurarnos de que la Partida se realiza en el mismo centro
				// que el del Usuario conectado
				if (partida.getSolicitudPartida().getUsuarioOrganizador().getCentroUsuario().getIdCentro() == conectado
						.getCentroUsuario().getIdCentro()) {

					// Condicional para comprobar que la Partida no está marcada como terminada.
					if (partida.getEstadoPartida().getIdEstadoPartida() != 2) {

						// Lista con todos los jugadores que se han unido a la partida.
						List<Emparejamiento> emparejamientosPartida = emparejamientos.findByPartida(partida.getId());

						// Bucle foreach comprobando todos los emparejamientos de la partida
						for (Emparejamiento emparejamiento : emparejamientosPartida) {

							// Condicional para filtrar si el Usuario conectado está apuntado a la partida o
							// no.
							if (emparejamiento.getPartidaEmparejada() != partida.getId()
									&& !conectado.getEmail().equalsIgnoreCase(emparejamiento.getUsuarioEmparejado())) {

								// Si la Partida cumple todos los requisitos anteriores se le une a la coleccion
								// de Partidas filtrada.
								filtro.add(partida);

							}
						}
					}
				}
			}
		}
		// Devolvemos la lista con las Partidas ya filtradas.
		return filtro;
	}

	// Método que devuelve las partidas que están organizadas por el Usuario
	// conectado.
	public List<Partida> misPartidasOrganizadas(Usuario conectado) {

		// Lista con todas las partidas.
		List<Partida> todaPartida = repository.findAll();
		// Lista para almacenar las partidas filtradas.
		List<Partida> filtro = new ArrayList<Partida>();

		// Bucle foreach para recorrer todas las partidas.
		for (Partida partida : todaPartida) {

			// Condicional para comprobar que el Usuario Organizador de la partida es el
			// Usuario conectado.
			if (partida.getSolicitudPartida().getUsuarioOrganizador().getIdUsuario() == conectado.getIdUsuario()) {

				// Condicional para comprobar que el estado de la partida no es "Terminada".
				if (partida.getEstadoPartida().getIdEstadoPartida() != 2) {

					// Añadimos la partida a la lista si pasa todos los filtros.
					filtro.add(partida);
				}
			}
		}
		// Devolvemos la lista con todas las Partidas ya filtradas.
		return filtro;
	}

	// Método que devuelve las Partidas en las que el Usuario conectado ha
	// participado tanto como Jugador como Organizador.
	public List<Partida> historial(Usuario conectado) {

		// Lista con todas las partidas.
		List<Partida> todaPartida = repository.findAll();
		// Lista para almacenar las partidas filtradas.
		List<Partida> filtro = new ArrayList<Partida>();

		// Bucle foreach para recorrer todas las partidas.
		for (Partida partida : todaPartida) {

			// Comprobación de que la Partida pertenece al Centro del Usuario conectado.
			if (partida.getSolicitudPartida().getUsuarioOrganizador().getCentroUsuario().getIdCentro() == conectado
					.getCentroUsuario().getIdCentro()) {

				// Condicional que el que se comprueba que la Partida está marcada como
				// "Terminada".
				if (partida.getEstadoPartida().getIdEstadoPartida() == 2) {
					// Se añade la partida a la lista si cumple los requisitos.
					filtro.add(partida);
				}
			}
		}
		// Devolvemos la lista con todas las Partidas ya filtradas.
		return filtro;
	}

}
