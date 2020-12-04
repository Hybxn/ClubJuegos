package com.clubjuegos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "idiomas")
public class Idioma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_idiomas")
	private int idIdiomas;

	@Column(name="nombre_idioma")
	@Size(max = 30 , message = "El nombre es demasiado largo.")
	private String nombreIdioma;

	public int getIdIdiomas() {
		return idIdiomas;
	}

	public void setIdIdiomas(int idIdiomas) {
		this.idIdiomas = idIdiomas;
	}

	public String getNombreIdiomas() {
		return nombreIdioma;
	}

	public void setNombreIdiomas(String nombreIdiomas) {
		this.nombreIdioma = nombreIdiomas;
	}
	
	public Idioma() {
		
	}

	public Idioma(String nombreIdiomas) {
		this.nombreIdioma = nombreIdiomas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idIdiomas;
		result = prime * result + ((nombreIdioma == null) ? 0 : nombreIdioma.hashCode());
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
		Idioma other = (Idioma) obj;
		if (idIdiomas != other.idIdiomas)
			return false;
		if (nombreIdioma == null) {
			if (other.nombreIdioma != null)
				return false;
		} else if (!nombreIdioma.equals(other.nombreIdioma))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Idioma [idIdiomas=" + idIdiomas + ", nombreIdioma=" + nombreIdioma + "]";
	}
	
	
}
