package com.clubjuegos.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Emparejamiento;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.models.ValoracionJugador;
import com.clubjuegos.repositories.ValoracionJugadorRepository;
import com.clubjuegos.utils.Utilidades;

//Clase Servicio donde manejaremos los datos del repositorio de ValoracionJugador
@Service
public class ValoracionJugadorService {

	// Repositorio autocableado.
	@Autowired
	private ValoracionJugadorRepository repository;

	// Añadir un ValoracionJugador nuevo.
	public ValoracionJugador add(ValoracionJugador valoracion) {
		return repository.save(valoracion);
	}

	// Listar todos los ValoracionJugadors.
	public List<ValoracionJugador> findAll() {
		return repository.findAll();
	}

	// Encontrar un ValoracionJugador por ID o si no existe, devolver null
	public ValoracionJugador findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Editar un ValoracionJugador ya existe o guardarlo como nuevo.
	public ValoracionJugador edit(ValoracionJugador valoracion) {
		return repository.save(valoracion);
	}

	// Borrar un ValoracionJugador usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un ValoracionJugador mediante la busqueda de la propia instancia de
	// ValoracionJugador en el repositorio.
	public void deleteByElement(ValoracionJugador valoracion) {
		repository.delete(valoracion);
	}

	// Método que devuelve true si Existe una ValoracionActividad o false si no
	// existe.
	public boolean existeValoracion(Emparejamiento emparejamiento, Usuario conectado) {

		// Instanciamos el booleano.
		boolean existe = false;
		// Lista con todas las ValoracionJugador.
		List<ValoracionJugador> todaValoracion = repository.findAll();

		// Bucle foreach que recorre la lista anterior de ValoracionJugador.
		for (ValoracionJugador valoracionActividad : todaValoracion) {

			// Filtro que comprueba si el Usuario conectado ya ha valorado al Jugador en una
			// Partida en concreto.
			if (valoracionActividad.getEncuentroValoracion() == emparejamiento
					&& valoracionActividad.getJugadorValorador().getIdUsuario() == conectado.getIdUsuario()) {

				// Si la anterior condición se cumple cambiamos el booleano a true.
				existe = true;
				// Paramos la ejecución del bucle.
				break;
			}
		}
		// Devolvemos el valor del booleano.
		return existe;
	}

	// Método que devuelve un mapa con los pares email del Jugador y
	// ValoracionJugador
	// media del mismo.
	public Object topJugadores(UsuarioService usuarios) {

		// Instanciamos el HashMap que usaremos para combinar los Jugadores(emails)
		// con sus puntuaciones.
		Map<String, Double> topJugadores = new HashMap<String, Double>();

		// Lista con todas las ValoracionJugador
		List<ValoracionJugador> todaValoracion = repository.findAll();
		// Lista con todos los Usuarios
		List<Usuario> jugadores = usuarios.findAll();

		// Recorremos toda la lista de Usuarios.
		for (Usuario usuario : jugadores) {

			// Inicializamos unas variables para calcular la media.
			double i = 0;
			double total = 0;
			double media = 0;

			// Bucle que recorre todas la lista de ValoracionJugador
			for (ValoracionJugador valoracionJugador : todaValoracion) {

				// Condicional que comprueba si el Jugador de la valoración coincide con en
				// Jugador de la interacion del primer bucle.
				if (valoracionJugador.getEncuentroValoracion().getUsuarioEmparejado()
						.equalsIgnoreCase(usuario.getEmail())) {

					// Si se cumple la condición anterior añadimos +1 al contador para calcular la
					// media más adelante.
					i++;
					// Vamos acumulando todas las puntuaciones para despues dividirla.
					total += valoracionJugador.getPuntuacion();
				}
			}
			// Comprobamos que no se realice una división entre 0 o de 0/0.
			if (total != 0 && i != 0) {
				// Calculamos la media con una simple división.
				media = total / i;
				// Añadimos al HashMap el email del Jugador como Key y la media como Value.
				topJugadores.put(usuario.getEmail(), media);
			}
		}
		// Creamos otro Mapa para ordenar los Jugadores utilizando un método estático de
		// nuestra Clase Utilidades.
		Map<String, Double> ordenado = Utilidades.ordenarPorPuntuacion(topJugadores);
		// Devolvemos el Map ordenado.
		return ordenado;
	}

}
