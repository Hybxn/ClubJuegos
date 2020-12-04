package com.clubjuegos.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Juego;
import com.clubjuegos.models.Partida;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.models.ValoracionActividad;
import com.clubjuegos.repositories.ValoracionActividadRepository;
import com.clubjuegos.utils.Utilidades;

//Clase Servicio donde manejaremos los datos del repositorio de ValoracionActividad
@Service
public class ValoracionActividadService {

	// Repositorio autocableado.
	@Autowired
	private ValoracionActividadRepository repository;

	// Añadir un ValoracionActividad nuevo.
	public ValoracionActividad add(ValoracionActividad valoracion) {
		return repository.save(valoracion);
	}

	// Listar todos los ValoracionActividads.
	public List<ValoracionActividad> findAll() {
		return repository.findAll();
	}

	// Encontrar un ValoracionActividad por ID o si no existe, devolver null
	public ValoracionActividad findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Editar un ValoracionActividad ya existe o guardarlo como nuevo.
	public ValoracionActividad edit(ValoracionActividad valoracion) {
		return repository.save(valoracion);
	}

	// Borrar un ValoracionActividad usando un ID.
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un ValoracionActividad mediante la busqueda de la propia instancia de
	// ValoracionActividad en el repositorio.
	public void deleteByElement(ValoracionActividad valoracion) {
		repository.delete(valoracion);
	}

	// Método que devuelve true si Existe una ValoracionActividad o false si no
	// existe.
	public boolean existeValoracion(Partida partida, Usuario conectado) {

		// Instanciamos el booleano.
		boolean existe = false;

		// Lista con todas las ValoracionActividad.
		List<ValoracionActividad> todaValoracion = repository.findAll();

		// Bucle foreach que recorre la lista anterior de ValoracionActividad.
		for (ValoracionActividad valoracionActividad : todaValoracion) {

			// Filtro que comprueba si la Partida de la ValoracionActividad coincide con la
			// que ha seleccionado el Usuario para valorar y el Usuario de la
			// ValoracionActividad es el mismo que el usuario conectado.
			if (valoracionActividad.getPartidaValoracion().getId() == partida.getId()
					&& valoracionActividad.getUsuarioValoracion().getIdUsuario() == conectado.getIdUsuario()) {
				// Si la anterior condición se cumple cambiamos el booleano a true.
				existe = true;
				// Paramos la ejecución del bucle.
				break;
			}
		}
		// Devolvemos el valor del booleano.
		return existe;
	}

	// Método que devuelve un mapa con los pares nombre del Juego y valoracion media
	// del mismo.
	public Map<String, Double> topJuegos(JuegoService juegos) {

		// Instanciamos el HashMap que usaremos para combinar los juegos con sus
		// puntuaciones.
		Map<String, Double> topJuegos = new HashMap<String, Double>();
		// Lista con todas las ValoracionActividad
		List<ValoracionActividad> todaValoracion = repository.findAll();
		// Lista con todos los Juegos
		List<Juego> todoJuego = juegos.findAll();

		// Recorremos toda la lista de Juegos.
		for (Juego juego : todoJuego) {

			// Inicializamos unas variables para calcular la media.
			double i = 0;
			double total = 0;
			double media = 0;

			// Bucle que recorre todas la lista de ValoracionActividad
			for (ValoracionActividad valoracionActividad : todaValoracion) {

				// Condicional que comprueba si el Juego coincide con el Juego de
				// ValoracionActividad.
				if (juego.getIdJuegos() == valoracionActividad.getPartidaValoracion().getSolicitudPartida()
						.getJuegoSolicitud().getIdJuegos()) {
					// Si se cumple la condición anterior añadimos +1 al contador para calcular la
					// media más adelante.
					i++;
					// Vamos acumulando todas las puntuaciones para despues dividirla.
					total += valoracionActividad.getValoracionJuego();
				}
			}
			// Comprobamos que no se realice una división entre 0 o de 0/0.
			if (total != 0 && i != 0) {
				// Calculamos la media con una simple división.
				media = total / i;
				// Añadimos al HashMap el nombre del Juego como Key y la media como Value.
				topJuegos.put(juego.getNombreJuego(), media);
			}
		}
		// Creamos otro Mapa para ordenar los juegos utilizando un método estático de
		// nuestra Clase Utilidades.
		Map<String, Double> ordenado = Utilidades.ordenarPorPuntuacion(topJuegos);
		// Devolvemos el Map ordenado.
		return ordenado;
	}

	// Método que devuelve un mapa con los pares email del Organizador y valoracion
	// media del mismo.
	public Map<String, Double> topOrganizadores(UsuarioService usuarios) {

		// Instanciamos el HashMap que usaremos para combinar los organizadores(emails)
		// con sus puntuaciones.
		Map<String, Double> topOrganizadores = new HashMap<String, Double>();
		// Lista con todas las ValoracionActividad
		List<ValoracionActividad> todaValoracion = repository.findAll();
		// Lista con todos los Usuarios
		List<Usuario> organizadores = usuarios.findAll();

		// Recorremos toda la lista de Usuarios.
		for (Usuario usuario : organizadores) {

			// Inicializamos unas variables para calcular la media.
			int i = 0;
			int total = 0;
			double media = 0;

			// Bucle que recorre todas la lista de ValoracionActividad
			for (ValoracionActividad valoracionActividad : todaValoracion) {

				// Condicional que comprueba si el Organizador coincide con el Usuario de
				// ValoracionActividad.
				if (valoracionActividad.getPartidaValoracion().getSolicitudPartida().getUsuarioOrganizador()
						.getIdUsuario() == usuario.getIdUsuario()) {

					// Si se cumple la condición anterior añadimos +1 al contador para calcular la
					// media más adelante.
					i++;
					// Vamos acumulando todas las puntuaciones para despues dividirla.
					total += valoracionActividad.getValoracionOrganizador();
				}
			}
			// Comprobamos que no se realice una división entre 0 o de 0/0.
			if (total != 0 && i != 0) {
				// Calculamos la media con una simple división.
				media = total / i;
				// Añadimos al HashMap el email del Organizador como Key y la media como Value.
				topOrganizadores.put(usuario.getEmail(), media);
			}
		}
		// Creamos otro Mapa para ordenar los organizadores utilizando un método estático de
		// nuestra Clase Utilidades.
		Map<String, Double> ordenado = Utilidades.ordenarPorPuntuacion(topOrganizadores);
		// Devolvemos el Map ordenado.
		return ordenado;
	}

}
