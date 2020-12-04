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

@Entity
@Table(name = "valoracion_jugador")
public class ValoracionJugador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idvaloracion_jugador")
	private int idValoracionJugador;

	@Column(name = "puntuacion")
	private int puntuacion;

	@ManyToOne
	@JoinColumn(name = "encuentro_valoracion", foreignKey = @ForeignKey(name = "fk_encuentro_valoracion"))
	private Emparejamiento encuentroValoracion;

	@ManyToOne
	@JoinColumn(name = "jugador_valorador", foreignKey = @ForeignKey(name = "fk_jugador_valorador"))
	private Usuario jugadorValorador;

	public ValoracionJugador() {
	}

	public ValoracionJugador(int puntuacion, Emparejamiento encuentroValoracion, Usuario jugadorValorador) {
		this.puntuacion = puntuacion;
		this.encuentroValoracion = encuentroValoracion;
		this.jugadorValorador = jugadorValorador;
	}

	public ValoracionJugador(Emparejamiento encuentroValoracion, Usuario jugadorValorador) {
		this.encuentroValoracion = encuentroValoracion;
		this.jugadorValorador = jugadorValorador;
	}

	public int getIdValoracionJugador() {
		return idValoracionJugador;
	}

	public void setIdValoracionJugador(int idValoracionJugador) {
		this.idValoracionJugador = idValoracionJugador;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Emparejamiento getEncuentroValoracion() {
		return encuentroValoracion;
	}

	public void setEncuentroValoracion(Emparejamiento encuentroValoracion) {
		this.encuentroValoracion = encuentroValoracion;
	}

	public Usuario getJugadorValorador() {
		return jugadorValorador;
	}

	public void setJugadorValorador(Usuario jugadorValorador) {
		this.jugadorValorador = jugadorValorador;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((encuentroValoracion == null) ? 0 : encuentroValoracion.hashCode());
		result = prime * result + idValoracionJugador;
		result = prime * result + ((jugadorValorador == null) ? 0 : jugadorValorador.hashCode());
		result = prime * result + puntuacion;
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
		ValoracionJugador other = (ValoracionJugador) obj;
		if (encuentroValoracion == null) {
			if (other.encuentroValoracion != null)
				return false;
		} else if (!encuentroValoracion.equals(other.encuentroValoracion))
			return false;
		if (idValoracionJugador != other.idValoracionJugador)
			return false;
		if (jugadorValorador == null) {
			if (other.jugadorValorador != null)
				return false;
		} else if (!jugadorValorador.equals(other.jugadorValorador))
			return false;
		if (puntuacion != other.puntuacion)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValoracionJugador [idValoracionJugador=" + idValoracionJugador + ", puntuacion=" + puntuacion
				+ ", encuentroValoracion=" + encuentroValoracion + ", jugadorValorador=" + jugadorValorador + "]";
	}
	
	
}
