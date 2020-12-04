package com.clubjuegos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "roles")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private int idRol;

	@Column(name = "nombre_rol")
	@Size(max = 60 , message = "El nombre es demasiado largo.")
	private String nombreRol;

	@Column(name = "descripcion_rol")
	@Size(max = 150 , message = "La descripcion es demasiado larga.")
	private String descripcionRol;

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public Rol() {

	}

	public Rol(String nombreRol, String descripcionRol) {
		this.nombreRol = nombreRol;
		this.descripcionRol = descripcionRol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcionRol == null) ? 0 : descripcionRol.hashCode());
		result = prime * result + idRol;
		result = prime * result + ((nombreRol == null) ? 0 : nombreRol.hashCode());
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
		Rol other = (Rol) obj;
		if (descripcionRol == null) {
			if (other.descripcionRol != null)
				return false;
		} else if (!descripcionRol.equals(other.descripcionRol))
			return false;
		if (idRol != other.idRol)
			return false;
		if (nombreRol == null) {
			if (other.nombreRol != null)
				return false;
		} else if (!nombreRol.equals(other.nombreRol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", nombreRol=" + nombreRol + ", descripcionRol=" + descripcionRol + "]";
	}

	
}
