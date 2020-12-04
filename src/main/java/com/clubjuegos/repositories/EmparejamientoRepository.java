package com.clubjuegos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clubjuegos.models.Emparejamiento;

public interface EmparejamientoRepository  extends JpaRepository<Emparejamiento, Integer>{

	@Query(value = "SELECT * FROM emparejamientos e WHERE e.partida_emparejada = :partida", nativeQuery = true)
	List<Emparejamiento> findByPartida(@Param("partida") int idPartida);

}
