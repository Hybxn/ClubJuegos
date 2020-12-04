package com.clubjuegos.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "solicitudes")
public class Solicitud {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_solicitud")
	private int idSolicitud;

	@Column(name = "dia_partida")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Debe seleccionar una fecha.")
	private Date diaPartida;

	@Column(name = "hora_partida")
	@Pattern(regexp = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Debes introducir una hora válida.")
	private String horaPartida;

	@Column(name = "duracion_estimada")
	@Max(value = 999999999, message = "Mucho tiempo.")
	private int duracionEstimada;

	@ManyToOne
	@JoinColumn(name = "usuario_organizador", foreignKey = @ForeignKey(name = "fk_usuario_organizador_idx"))
	private Usuario usuarioOrganizador;

	@ManyToOne
	@JoinColumn(name = "juego_solicitud", foreignKey = @ForeignKey(name = "fk_juego_solicitud_idx"))
	private Juego juegoSolicitud;

	@ManyToOne
	@JoinColumn(name = "estado_solicitud", foreignKey = @ForeignKey(name = "fk_estado_solicitud_idx"))
	private EstadosSolicitud estadoSolicitud;

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public Date getDiaPartida() {
		return diaPartida;
	}

	public void setDiaPartida(Date diaPartida) {
		this.diaPartida = diaPartida;
	}

	public String getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}

	public int getDuracionEstimada() {
		return duracionEstimada;
	}

	public void setDuracionEstimada(int duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	public Usuario getUsuarioOrganizador() {
		return usuarioOrganizador;
	}

	public void setUsuarioOrganizador(Usuario usuarioOrganizador) {
		this.usuarioOrganizador = usuarioOrganizador;
	}

	public Juego getJuegoSolicitud() {
		return juegoSolicitud;
	}

	public void setJuegoSolicitud(Juego juegoSolicitud) {
		this.juegoSolicitud = juegoSolicitud;
	}

	public EstadosSolicitud getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(EstadosSolicitud estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public Solicitud(Date diaPartida, String horaPartida, int duracionEstimada, Usuario usuarioOrganizador,
			Juego juegoSolicitud, EstadosSolicitud estadoSolicitud) {
		this.diaPartida = diaPartida;
		this.horaPartida = horaPartida;
		this.duracionEstimada = duracionEstimada;
		this.usuarioOrganizador = usuarioOrganizador;
		this.juegoSolicitud = juegoSolicitud;
		this.estadoSolicitud = estadoSolicitud;
	}

	public Solicitud() {
	}

	@Override
	public String toString() {
		return "Solicitud [idSolicitud=" + idSolicitud + ", diaPartida=" + diaPartida + ", horaPartida=" + horaPartida
				+ ", duracionEstimada=" + duracionEstimada + ", usuarioOrganizador=" + usuarioOrganizador
				+ ", juegoSolicitud=" + juegoSolicitud + ", estadoSolicitud=" + estadoSolicitud + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diaPartida == null) ? 0 : diaPartida.hashCode());
		result = prime * result + duracionEstimada;
		result = prime * result + ((estadoSolicitud == null) ? 0 : estadoSolicitud.hashCode());
		result = prime * result + ((horaPartida == null) ? 0 : horaPartida.hashCode());
		result = prime * result + idSolicitud;
		result = prime * result + ((juegoSolicitud == null) ? 0 : juegoSolicitud.hashCode());
		result = prime * result + ((usuarioOrganizador == null) ? 0 : usuarioOrganizador.hashCode());
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
		Solicitud other = (Solicitud) obj;
		if (diaPartida == null) {
			if (other.diaPartida != null)
				return false;
		} else if (!diaPartida.equals(other.diaPartida))
			return false;
		if (duracionEstimada != other.duracionEstimada)
			return false;
		if (estadoSolicitud == null) {
			if (other.estadoSolicitud != null)
				return false;
		} else if (!estadoSolicitud.equals(other.estadoSolicitud))
			return false;
		if (horaPartida == null) {
			if (other.horaPartida != null)
				return false;
		} else if (!horaPartida.equals(other.horaPartida))
			return false;
		if (idSolicitud != other.idSolicitud)
			return false;
		if (juegoSolicitud == null) {
			if (other.juegoSolicitud != null)
				return false;
		} else if (!juegoSolicitud.equals(other.juegoSolicitud))
			return false;
		if (usuarioOrganizador == null) {
			if (other.usuarioOrganizador != null)
				return false;
		} else if (!usuarioOrganizador.equals(other.usuarioOrganizador))
			return false;
		return true;
	}

}
