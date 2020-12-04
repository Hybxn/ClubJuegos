package com.clubjuegos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "incidencias")
public class Incidencia {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "id_incidencias")
	private int idIncidencias;
	
	@Column( name = "descr_incidencia")
	@Size(max = 255 , message = "La descripcion es demasiado larga.")
	private String descrIncidencia;

	@ManyToOne
	@JoinColumn(name = "partida_incidencia",
				foreignKey = @ForeignKey(name = "fk_partida_incidencia_idx"))
	private Partida partidaIncidencia; 

	@ManyToOne
	@JoinColumn(name = "estado_incidencia",
				foreignKey = @ForeignKey(name = "fk_estado_incidencia_idx"))
	private EstadosIncidencia estadoIncidencia;

	public Incidencia(String descrIncidencia, Partida partidaIncidencia, EstadosIncidencia estadoIncidencia) {
		this.descrIncidencia = descrIncidencia;
		this.partidaIncidencia = partidaIncidencia;
		this.estadoIncidencia = estadoIncidencia;
	}

	public Incidencia() {
	}	

	public Incidencia(Partida partidaIncidencia, EstadosIncidencia estadoIncidencia) {
		this.partidaIncidencia = partidaIncidencia;
		this.estadoIncidencia = estadoIncidencia;
	}

	public int getIdIncidencias() {
		return idIncidencias;
	}

	public void setIdIncidencias(int idIncidencias) {
		this.idIncidencias = idIncidencias;
	}

	public String getDescrIncidencia() {
		return descrIncidencia;
	}

	public void setDescrIncidencia(String descrIncidencia) {
		this.descrIncidencia = descrIncidencia;
	}

	public void setPartidaIncidencia(Partida partidaIncidencia) {
		this.partidaIncidencia = partidaIncidencia;
	}

	public Partida getPartidaIncidencia() {
		return partidaIncidencia;
	}

	public EstadosIncidencia getEstadoIncidencia() {
		return estadoIncidencia;
	}

	public void setEstadoIncidencia(EstadosIncidencia estadoIncidencia) {
		this.estadoIncidencia = estadoIncidencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrIncidencia == null) ? 0 : descrIncidencia.hashCode());
		result = prime * result + ((estadoIncidencia == null) ? 0 : estadoIncidencia.hashCode());
		result = prime * result + idIncidencias;
		result = prime * result + ((partidaIncidencia == null) ? 0 : partidaIncidencia.hashCode());
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
		Incidencia other = (Incidencia) obj;
		if (descrIncidencia == null) {
			if (other.descrIncidencia != null)
				return false;
		} else if (!descrIncidencia.equals(other.descrIncidencia))
			return false;
		if (estadoIncidencia == null) {
			if (other.estadoIncidencia != null)
				return false;
		} else if (!estadoIncidencia.equals(other.estadoIncidencia))
			return false;
		if (idIncidencias != other.idIncidencias)
			return false;
		if (partidaIncidencia == null) {
			if (other.partidaIncidencia != null)
				return false;
		} else if (!partidaIncidencia.equals(other.partidaIncidencia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Incidencia [idIncidencias=" + idIncidencias + ", descrIncidencia=" + descrIncidencia
				+ ", partidaIncidencia=" + partidaIncidencia + ", estadoIncidencia=" + estadoIncidencia + "]";
	}
	
	
}
