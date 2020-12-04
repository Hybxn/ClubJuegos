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
@Table(name = "valoracion_actividad")
public class ValoracionActividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idvaloracion_actividad")
	private int idValoracionActividad;

	@Column(name = "valoracion_juego")
	private int valoracionJuego;

	@Column(name = "valoracion_organizador")
	private int valoracionOrganizador;

	@ManyToOne
	@JoinColumn(name = "partida_valoracion", foreignKey = @ForeignKey(name = "fk_partida_valoracion"))
	private Partida partidaValoracion;

	@ManyToOne
	@JoinColumn(name = "usuario_valoracion", foreignKey = @ForeignKey(name = "fk_usuario_valoracion"))
	private Usuario usuarioValoracion;

	public ValoracionActividad() {
	}

	public ValoracionActividad(int valoracionJuego, int valoracionOrganizador, Partida partidaValoracion,
			Usuario usuarioValoracion) {
		this.valoracionJuego = valoracionJuego;
		this.valoracionOrganizador = valoracionOrganizador;
		this.partidaValoracion = partidaValoracion;
		this.usuarioValoracion = usuarioValoracion;
	}

	public ValoracionActividad(int valoracionJuego, int valoracionOrganizador, Partida partidaValoracion) {
		this.valoracionJuego = valoracionJuego;
		this.valoracionOrganizador = valoracionOrganizador;
		this.partidaValoracion = partidaValoracion;
	}

	public ValoracionActividad(Partida partidaValoracion) {
		this.partidaValoracion = partidaValoracion;
	}

	public ValoracionActividad(Partida partidaValoracion, Usuario usuarioValoracion) {
		this.partidaValoracion = partidaValoracion;
		this.usuarioValoracion = usuarioValoracion;
	}

	public Usuario getUsuarioValoracion() {
		return usuarioValoracion;
	}

	public void setUsuarioValoracion(Usuario usuarioValoracion) {
		this.usuarioValoracion = usuarioValoracion;
	}

	public int getIdValoracionActividad() {
		return idValoracionActividad;
	}

	public void setIdValoracionActividad(int idValoracionActividad) {
		this.idValoracionActividad = idValoracionActividad;
	}

	public int getValoracionJuego() {
		return valoracionJuego;
	}

	public void setValoracionJuego(int valoracionJuego) {
		this.valoracionJuego = valoracionJuego;
	}

	public int getValoracionOrganizador() {
		return valoracionOrganizador;
	}

	public void setValoracionOrganizador(int valoracionOrganizador) {
		this.valoracionOrganizador = valoracionOrganizador;
	}

	public Partida getPartidaValoracion() {
		return partidaValoracion;
	}

	public void setPartidaValoracion(Partida partidaValoracion) {
		this.partidaValoracion = partidaValoracion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idValoracionActividad;
		result = prime * result + ((partidaValoracion == null) ? 0 : partidaValoracion.hashCode());
		result = prime * result + ((usuarioValoracion == null) ? 0 : usuarioValoracion.hashCode());
		result = prime * result + valoracionJuego;
		result = prime * result + valoracionOrganizador;
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
		ValoracionActividad other = (ValoracionActividad) obj;
		if (idValoracionActividad != other.idValoracionActividad)
			return false;
		if (partidaValoracion == null) {
			if (other.partidaValoracion != null)
				return false;
		} else if (!partidaValoracion.equals(other.partidaValoracion))
			return false;
		if (usuarioValoracion == null) {
			if (other.usuarioValoracion != null)
				return false;
		} else if (!usuarioValoracion.equals(other.usuarioValoracion))
			return false;
		if (valoracionJuego != other.valoracionJuego)
			return false;
		if (valoracionOrganizador != other.valoracionOrganizador)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ValoracionActividad [idValoracionActividad=" + idValoracionActividad + ", valoracionJuego="
				+ valoracionJuego + ", valoracionOrganizador=" + valoracionOrganizador + ", partidaValoracion="
				+ partidaValoracion + ", usuarioValoracion=" + usuarioValoracion + "]";
	}

}
