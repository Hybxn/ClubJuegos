package com.clubjuegos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estadospartidas")
public class EstadosPartida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estados_partidas")
	private int idEstadoPartida;

	@Column(name = "nombre_estado_partida")
	@Size(max = 25, message = "El nombre es demasiado largo.")
	private String nombreEstadoPartida;

	@Column(name = "descr_estado_partida")
	@Size(max = 255 , message = "La descripcion es demasiado larga.")
	private String descripcionEstadoPartida;

	public int getIdEstadoPartida() {
		return idEstadoPartida;
	}

	public void setIdEstadoPartida(int idEstadoPartida) {
		this.idEstadoPartida = idEstadoPartida;
	}

	public String getNombreEstadoPartida() {
		return nombreEstadoPartida;
	}

	public void setNombreEstadoPartida(String nombreEstadoPartida) {
		this.nombreEstadoPartida = nombreEstadoPartida;
	}

	public String getDescripcionEstadoPartida() {
		return descripcionEstadoPartida;
	}

	public void setDescripcionEstadoPartida(String descripcionEstadoPartida) {
		this.descripcionEstadoPartida = descripcionEstadoPartida;
	}

	public EstadosPartida() {
	}

	public EstadosPartida(String nombreEstadoPartida, String descripcionEstadoPartida) {
		this.nombreEstadoPartida = nombreEstadoPartida;
		this.descripcionEstadoPartida = descripcionEstadoPartida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcionEstadoPartida == null) ? 0 : descripcionEstadoPartida.hashCode());
		result = prime * result + idEstadoPartida;
		result = prime * result + ((nombreEstadoPartida == null) ? 0 : nombreEstadoPartida.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadosPartida other = (EstadosPartida) obj;
		if (descripcionEstadoPartida == null) {
			if (other.descripcionEstadoPartida != null)
				return false;
		} else if (!descripcionEstadoPartida.equals(other.descripcionEstadoPartida))
			return false;
		if (idEstadoPartida != other.idEstadoPartida)
			return false;
		if (nombreEstadoPartida == null) {
			if (other.nombreEstadoPartida != null)
				return false;
		} else if (!nombreEstadoPartida.equals(other.nombreEstadoPartida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstadosPartida [idEstadoPartida=" + idEstadoPartida + ", nombreEstadoPartida=" + nombreEstadoPartida
				+ ", descripcionEstadoPartida=" + descripcionEstadoPartida + "]";
	}
	
	

	
	
}
