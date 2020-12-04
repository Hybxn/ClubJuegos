package com.clubjuegos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Incidencia;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.repositories.IncidenciaRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Incidencias
@Service
public class IncidenciaService {

	//Repositorio autocableado.
	@Autowired
	private IncidenciaRepository repository;

	//Añadir un Incidencias nuevo.
	public Incidencia add(Incidencia incidencia) {
		return repository.save(incidencia);
	}

	//Listar todos los Incidenciass.
	public List<Incidencia> findAll() {
		return repository.findAll();
	}

	//Encontrar un Incidencias por ID o si no existe, devolver null
	public Incidencia findById(int id) {
		return repository.findById(id).orElse(null);
	}

	//Editar un Incidencias ya existe o guardarlo como nuevo.
	public Incidencia edit(Incidencia incidencia) {
		return repository.save(incidencia);
	}

	//Borrar un Incidencias usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	//Borrar un Incidencias mediante la busqueda del propio Incidencias en el repositorio.
	public void deleteByElement(Incidencia incidencia) {
		repository.delete(incidencia);
	}

	//Listar las incidencias del Usuario Responsable de Mantenimiento conectado.
	public List<Incidencia> incidenciasMiCentro(Usuario conectado) {
		//Lista con todas las incidencias
		List<Incidencia> todaIncidencia = repository.findAll();
		//Lista vacia para añadir las incidencias filtradas.
		List<Incidencia> filtro = new ArrayList<Incidencia>();

		//Bucle para recorrer todas la incidencias
		for (Incidencia incidencia : todaIncidencia) {

			//Condicional que compara el ID del Centro del Usuario conectado con el ID del Centro de la Incidencia
			if (incidencia.getPartidaIncidencia().getSolicitudPartida().getJuegoSolicitud().getCentroJuego()
					.getIdCentro() == conectado.getCentroUsuario().getIdCentro()) {
				//Si la incidencia cumple la anterior condición la añadimos a la lista que habiamos creado antes.
				filtro.add(incidencia);

			}
		}
		//Devolvemos la lista donde están las incidencias ya filtradas.
		return filtro;
	}

}
