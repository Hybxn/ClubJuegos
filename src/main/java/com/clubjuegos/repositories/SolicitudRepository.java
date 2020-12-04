package com.clubjuegos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clubjuegos.models.EstadosSolicitud;
import com.clubjuegos.models.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

	List<Solicitud> findByEstadoSolicitudLike(EstadosSolicitud estado);

	@Query(value = "SELECT * FROM solicitudes s WHERE s.juego_solicitud IN :juegos and s.estado_solicitud = :estado", nativeQuery = true)
	List<Solicitud> findByCenter(@Param("juegos") List<Integer> juegos, @Param("estado") Integer idEstado);
	
	@Query(value = "SELECT * FROM solicitudes s WHERE s.usuario_organizador = :usuario", nativeQuery = true)
	List<Solicitud> findByUsuario(@Param("usuario") Integer idUsuario);

}
