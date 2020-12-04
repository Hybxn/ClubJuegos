package com.clubjuegos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estadosincidencias")
public class EstadosIncidencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estados_incidencia")
	private int idEstadoIncidencia;

	@Column(name = "nombre_estado_incidencia")
	@Size(max = 25, message = "El nombre es demasiado largo.")
	private String nombreEstadoIncidencia;

	@Column(name = "descripcion_estado_incidencia")
	@Size(max = 255 , message = "La descripcion es demasiado larga.")
	private String descripcionEstadoIncidencia;

	public int getIdEstadoIncidencia() {
		return idEstadoIncidencia;
	}

	public void setIdEstadoIncidencia(int idEstadoIncidencia) {
		this.idEstadoIncidencia = idEstadoIncidencia;
	}

	public String getNombreEstadoIncidencia() {
		return nombreEstadoIncidencia;
	}

	public void setNombreEstadoIncidencia(String nombreEstadoIncidencia) {
		this.nombreEstadoIncidencia = nombreEstadoIncidencia;
	}

	public String getDescripcionEstadoIncidencia() {
		return descripcionEstadoIncidencia;
	}

	public void setDescripcionEstadoIncidencia(String descripcionEstadoIncidencia) {
		this.descripcionEstadoIncidencia = descripcionEstadoIncidencia;
	}

	public EstadosIncidencia(String nombreEstadoIncidencia, String descripcionEstadoIncidencia) {
		this.nombreEstadoIncidencia = nombreEstadoIncidencia;
		this.descripcionEstadoIncidencia = descripcionEstadoIncidencia;
	}

	public EstadosIncidencia() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcionEstadoIncidencia == null) ? 0 : descripcionEstadoIncidencia.hashCode());
		result = prime * result + idEstadoIncidencia;
		result = prime * result + ((nombreEstadoIncidencia == null) ? 0 : nombreEstadoIncidencia.hashCode());
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
		EstadosIncidencia other = (EstadosIncidencia) obj;
		if (descripcionEstadoIncidencia == null) {
			if (other.descripcionEstadoIncidencia != null)
				return false;
		} else if (!descripcionEstadoIncidencia.equals(other.descripcionEstadoIncidencia))
			return false;
		if (idEstadoIncidencia != other.idEstadoIncidencia)
			return false;
		if (nombreEstadoIncidencia == null) {
			if (other.nombreEstadoIncidencia != null)
				return false;
		} else if (!nombreEstadoIncidencia.equals(other.nombreEstadoIncidencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstadosIncidencia [idEstadoIncidencia=" + idEstadoIncidencia + ", nombreEstadoIncidencia="
				+ nombreEstadoIncidencia + ", descripcionEstadoIncidencia=" + descripcionEstadoIncidencia + "]";
	}

}
