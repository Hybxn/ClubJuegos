package com.clubjuegos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clubjuegos.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmail(String email);

	Optional<Usuario> findByNombre(String nombre);

	@Query(value = "SELECT * FROM usuarios u WHERE u.centro_usuario = :centro", nativeQuery = true)
	List<Usuario> findByCentro(@Param("centro") Integer idCentro);
	
	@Query(value = "SELECT * FROM usuarios u WHERE u.centro_usuario = :centro AND u.rol_usuario = 3", nativeQuery = true)
	Usuario findCoordinador(@Param("centro") Integer idCentro);
}
