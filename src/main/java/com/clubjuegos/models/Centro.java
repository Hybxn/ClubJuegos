package com.clubjuegos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "centros")
public class Centro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_centro")
	private int idCentro;

	@Column(name = "pais")
	@NotEmpty(message = "Debes indicar el país donde se ubica el centro")
	@Size(max = 50, message = "Debes indicar el código postal correspondiente al centro")
	private String pais;

	@Column(name = "ciudad")
	@NotEmpty(message = "Debes indicar la ciudad donde se ubica el centro")
	@Size(max = 50, message = "Debes indicar el código postal correspondiente al centro")
	private String ciudad;

	@Column(name = "direccion")
	@NotEmpty(message = "Debes indicar la dirección donde se ubica el centro")
	@Size(max = 50, message = "Debes indicar el código postal correspondiente al centro")
	private String direccion;

	@Column(name = "codigo_postal")
	@NotEmpty(message = "Debes indicar el código postal correspondiente al centro")
	@Size(max = 50, message = "Debes indicar el código postal correspondiente al centro")
	private String codigoPostal;

	public int getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(int idCentro) {
		this.idCentro = idCentro;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public Centro(String pais, String ciudad, String direccion, String codigoPostal) {
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
	}

	public Centro() {

	}

	@Override
	public String toString() {
		return "Centros [idCentro=" + idCentro + ", pais=" + pais + ", ciudad=" + ciudad + ", direccion=" + direccion
				+ ", codigoPostal=" + codigoPostal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((codigoPostal == null) ? 0 : codigoPostal.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + idCentro;
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
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
		Centro other = (Centro) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (codigoPostal == null) {
			if (other.codigoPostal != null)
				return false;
		} else if (!codigoPostal.equals(other.codigoPostal))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (idCentro != other.idCentro)
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}
	
	

}
