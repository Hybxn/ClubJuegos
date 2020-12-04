package com.clubjuegos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clubjuegos.models.Juego;

public interface JuegoRepository extends JpaRepository<Juego, Integer>{

	@Query(value = "SELECT * FROM juegos j WHERE j.centro_juego = :centro", nativeQuery = true)
	List<Juego> findByCentro(@Param("centro") int idCentro);
	
	@Query(value = "SELECT id_juego FROM juegos j WHERE j.centro_juego = :centro", nativeQuery = true)
	List<Integer> findCodigoByCentro(@Param("centro") int idCentro);
}
