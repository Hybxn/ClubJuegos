package com.clubjuegos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "estadossolicitud")
public class EstadosSolicitud {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estados_solicitud")
	private int idEstadoSolicitud;

	@Column(name = "nombre_estado")
	@Size(max = 20, message = "El nombre es demasiado largo.")
	private String nombreEstado;

	@Column(name = "descripcion_estado")
	@Size(max = 255 , message = "La descripcion es demasiado larga.")
	private String descripcionEstado;

	public int getIdEstadoSolicitud() {
		return idEstadoSolicitud;
	}

	public void setIdEstadoSolicitud(int idEstadoSolicitud) {
		this.idEstadoSolicitud = idEstadoSolicitud;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getDescripcionEstado() {
		return descripcionEstado;
	}

	public void setDescripcionEstado(String descripcionEstado) {
		this.descripcionEstado = descripcionEstado;
	}

	public EstadosSolicitud() {

	}

	public EstadosSolicitud(String nombreEstado, String descripcionEstado) {
		this.nombreEstado = nombreEstado;
		this.descripcionEstado = descripcionEstado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcionEstado == null) ? 0 : descripcionEstado.hashCode());
		result = prime * result + idEstadoSolicitud;
		result = prime * result + ((nombreEstado == null) ? 0 : nombreEstado.hashCode());
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
		EstadosSolicitud other = (EstadosSolicitud) obj;
		if (descripcionEstado == null) {
			if (other.descripcionEstado != null)
				return false;
		} else if (!descripcionEstado.equals(other.descripcionEstado))
			return false;
		if (idEstadoSolicitud != other.idEstadoSolicitud)
			return false;
		if (nombreEstado == null) {
			if (other.nombreEstado != null)
				return false;
		} else if (!nombreEstado.equals(other.nombreEstado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EstadosSolicitud [idEstadoSolicitud=" + idEstadoSolicitud + ", nombreEstado=" + nombreEstado
				+ ", descripcionEstado=" + descripcionEstado + "]";
	}

}
