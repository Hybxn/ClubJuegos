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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "juegos")
public class Juego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_juego")
	private int idJuegos;

	@Column(name = "nombre_juego")
	@Size(max = 50 , message = "La nombre es demasiado larga.")
	private String nombreJuego;

	@Column(name = "descripcion_juego")
	@Size(max = 65535 , message = "La descripcion es demasiado larga.")
	private String descripcionJuego;

	@Column(name = "min_jugadores")
	@Min(value = 2, message = "No puede haber un juego de menos de 2 jugadores.")
	@Max(value = 999999999, message = "No puede haber un juego con tantos jugadores.")
	private int minJugadores;

	@Column(name = "max_jugadores")
	@Min(value = 2, message = "No puede haber un juego de menos de 2 jugadores.")
	@Max(value = 999999999, message = "No puede haber un juego con tantos jugadores.")
	private int maxJugadores;

	@Column(name = "stock")
	@Min(value = 1, message = "No puede haber menos de 1 juego.")
	@Max(value = 999999999, message = "No puede haber tantos juegos.")
	private int stock;

	@ManyToOne
	@JoinColumn(name = "centro_juego",
				foreignKey = @ForeignKey(name = "fk_centro_juego_idx"))
	private Centro centroJuego;


	@ManyToOne
	@JoinColumn(name = "idioma_juego",
				foreignKey = @ForeignKey(name="fk_idioma_juego_idx"))
	private Idioma idiomaJuego;

	public int getIdJuegos() {
		return idJuegos;
	}

	public void setIdJuegos(int idJuegos) {
		this.idJuegos = idJuegos;
	}

	public String getNombreJuego() {
		return nombreJuego;
	}

	public void setNombreJuego(String nombreJuego) {
		this.nombreJuego = nombreJuego;
	}

	public String getDescripcionJuego() {
		return descripcionJuego;
	}

	public void setDescripcionJuego(String descripcionJuego) {
		this.descripcionJuego = descripcionJuego;
	}

	public int getMinJugadores() {
		return minJugadores;
	}

	public void setMinJugadores(int minJugadores) {
		this.minJugadores = minJugadores;
	}

	public int getMaxJugadores() {
		return maxJugadores;
	}

	public void setMaxJugadores(int maxJugadores) {
		this.maxJugadores = maxJugadores;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Centro getCentroJuego() {
		return centroJuego;
	}

	public void setCentroJuego(Centro centroJuego) {
		this.centroJuego = centroJuego;
	}

	public Idioma getIdiomaJuego() {
		return idiomaJuego;
	}

	public void setIdiomaJuego(Idioma idiomaJuego) {
		this.idiomaJuego = idiomaJuego;
	}

	public Juego(String nombreJuego, String descripcionJuego, int minJugadores, int maxJugadores, int stock,
			Centro centroJuego, Idioma idiomaJuego) {
		this.nombreJuego = nombreJuego;
		this.descripcionJuego = descripcionJuego;
		this.minJugadores = minJugadores;
		this.maxJugadores = maxJugadores;
		this.stock = stock;
		this.centroJuego = centroJuego;
		this.idiomaJuego = idiomaJuego;
	}

	public Juego() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centroJuego == null) ? 0 : centroJuego.hashCode());
		result = prime * result + ((descripcionJuego == null) ? 0 : descripcionJuego.hashCode());
		result = prime * result + idJuegos;
		result = prime * result + ((idiomaJuego == null) ? 0 : idiomaJuego.hashCode());
		result = prime * result + maxJugadores;
		result = prime * result + minJugadores;
		result = prime * result + ((nombreJuego == null) ? 0 : nombreJuego.hashCode());
		result = prime * result + stock;
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
		Juego other = (Juego) obj;
		if (centroJuego == null) {
			if (other.centroJuego != null)
				return false;
		} else if (!centroJuego.equals(other.centroJuego))
			return false;
		if (descripcionJuego == null) {
			if (other.descripcionJuego != null)
				return false;
		} else if (!descripcionJuego.equals(other.descripcionJuego))
			return false;
		if (idJuegos != other.idJuegos)
			return false;
		if (idiomaJuego == null) {
			if (other.idiomaJuego != null)
				return false;
		} else if (!idiomaJuego.equals(other.idiomaJuego))
			return false;
		if (maxJugadores != other.maxJugadores)
			return false;
		if (minJugadores != other.minJugadores)
			return false;
		if (nombreJuego == null) {
			if (other.nombreJuego != null)
				return false;
		} else if (!nombreJuego.equals(other.nombreJuego))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Juego [idJuegos=" + idJuegos + ", nombreJuego=" + nombreJuego + ", descripcionJuego=" + descripcionJuego
				+ ", minJugadores=" + minJugadores + ", maxJugadores=" + maxJugadores + ", stock=" + stock
				+ ", centroJuego=" + centroJuego + ", idiomaJuego=" + idiomaJuego + "]";
	}

}
