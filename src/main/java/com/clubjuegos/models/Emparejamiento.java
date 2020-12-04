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
@Table( name = "emparejamientos")
public class Emparejamiento {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	@Column( name = "id_emparejamientos")
	private int idEmparejamientos;


	@ManyToOne
	@JoinColumn(name = "partida_emparejada",
				foreignKey = @ForeignKey(name = "fk_partida_emparejada_idx"))
	private Partida partidaEmparejada;

	@ManyToOne
	@JoinColumn(name = "usuario_emparejado",
				foreignKey = @ForeignKey(name = "fk_usuario_emparejado_idx"))
	private Usuario usuarioEmparejado;

	public Emparejamiento(Partida partidaEmparejada, Usuario usuarioEmparejado) {
		this.partidaEmparejada = partidaEmparejada;
		this.usuarioEmparejado = usuarioEmparejado;
	}

	public Emparejamiento() {
	}

	public int getIdEmparejamientos() {
		return idEmparejamientos;
	}

	public void setIdEmparejamientos(int idEmparejamientos) {
		this.idEmparejamientos = idEmparejamientos;
	}

	public int getPartidaEmparejada() {
		return partidaEmparejada.getId();
	}

	public void setPartidaEmparejada(Partida partidaEmparejada) {
		this.partidaEmparejada = partidaEmparejada;
	}

	public String getUsuarioEmparejado() {
		return usuarioEmparejado.getEmail();
	}

	public void setUsuarioEmparejado(Usuario usuarioEmparejado) {
		this.usuarioEmparejado = usuarioEmparejado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEmparejamientos;
		result = prime * result + ((partidaEmparejada == null) ? 0 : partidaEmparejada.hashCode());
		result = prime * result + ((usuarioEmparejado == null) ? 0 : usuarioEmparejado.hashCode());
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
		Emparejamiento other = (Emparejamiento) obj;
		if (idEmparejamientos != other.idEmparejamientos)
			return false;
		if (partidaEmparejada == null) {
			if (other.partidaEmparejada != null)
				return false;
		} else if (!partidaEmparejada.equals(other.partidaEmparejada))
			return false;
		if (usuarioEmparejado == null) {
			if (other.usuarioEmparejado != null)
				return false;
		} else if (!usuarioEmparejado.equals(other.usuarioEmparejado))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Emparejamiento [idEmparejamientos=" + idEmparejamientos + ", partidaEmparejada=" + partidaEmparejada
				+ ", usuarioEmparejado=" + usuarioEmparejado + "]";
	}
	
	
	
	

}
