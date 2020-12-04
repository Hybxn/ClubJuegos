package com.clubjuegos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clubjuegos.models.Partida;

public interface PartidaRepository  extends JpaRepository<Partida,Integer>{
	
}
