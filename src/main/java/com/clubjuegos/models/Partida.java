package com.clubjuegos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "partidas")
public class Partida {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_partida")
	private int id;

	@OneToOne
	@JoinColumn(name = "solicitud_partida")
	private Solicitud solicitudPartida;

	@ManyToOne
	@JoinColumn(name = "estado_partida", foreignKey = @ForeignKey(name = "fk_estado_partida_idx"))
	private EstadosPartida estadoPartida;

	public Partida(Solicitud solicitudPartida, EstadosPartida estadoPartida) {
		this.solicitudPartida = solicitudPartida;
		this.estadoPartida = estadoPartida;
	}

	public Partida() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Solicitud getSolicitudPartida() {
		return solicitudPartida;
	}

	public void setSolicitudPartida(Solicitud solicitudPartida) {
		this.solicitudPartida = solicitudPartida;
	}

	public EstadosPartida getEstadoPartida() {
		return estadoPartida;
	}

	public void setEstadoPartida(EstadosPartida estadoPartida) {
		this.estadoPartida = estadoPartida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estadoPartida == null) ? 0 : estadoPartida.hashCode());
		result = prime * result + id;
		result = prime * result + ((solicitudPartida == null) ? 0 : solicitudPartida.hashCode());
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
		Partida other = (Partida) obj;
		if (estadoPartida == null) {
			if (other.estadoPartida != null)
				return false;
		} else if (!estadoPartida.equals(other.estadoPartida))
			return false;
		if (id != other.id)
			return false;
		if (solicitudPartida == null) {
			if (other.solicitudPartida != null)
				return false;
		} else if (!solicitudPartida.equals(other.solicitudPartida))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partida [id=" + id + ", solicitudPartida=" + solicitudPartida + ", estadoPartida=" + estadoPartida
				+ "]";
	}
	

}
