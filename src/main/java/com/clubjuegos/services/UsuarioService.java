package com.clubjuegos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Usuario;
import com.clubjuegos.repositories.UsuarioRepository;

//Clase Servicio donde manejaremos los datos del repositorio de Usuario
@Service
public class UsuarioService {

	// Repositorio autocableado.
	@Autowired
	private UsuarioRepository repository;

	// Añadir un Usuario nuevo.
	public Usuario add(Usuario usuario) {
		return repository.save(usuario);
	}

	// Listar todos los Usuarios.
	public List<Usuario> findAll() {
		return repository.findAll();
	}

	// Encontrar un Usuario por ID o si no existe, devolver null
	public Usuario findById(int id) {
		return repository.findById(id).orElse(null);
	}

	// Encontrar un Usuario por Email o si no existe, devolver null
	public Optional<Usuario> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	// Encontrar un Usuario por Nombre o si no existe, devolver null
	public Optional<Usuario> findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}

	// Editar un Usuario ya existe o guardarlo como nuevo.
	public Usuario edit(Usuario usuario) {
		return repository.save(usuario);
	}
	
	// Borrar un Usuario usando un ID
	public void deleteById(int id) {
		repository.deleteById(id);
	}

	// Borrar un Usuario mediante la busqueda de la propia instancia de Usuario en el
	// repositorio.
	public void deleteByElement(Usuario usuario) {
		repository.delete(usuario);
	}
	
	//Listar todos los usuarios de un Centro.
	public List<Usuario> findByCentro(int idCentro){
		return repository.findByCentro(idCentro);
	}
	
	//Encontrar al usuario con el rol Coordinador de un Centro.
	public Usuario findCoordinador(int idCentro) {
		return repository.findCoordinador(idCentro);
	}

}
