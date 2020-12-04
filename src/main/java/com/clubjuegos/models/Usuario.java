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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private int idUsuario;

	@Column(name = "nombre")
	@NotEmpty(message = "Debes indicar tu nombre completo")
	@Pattern(regexp = "^[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð ,.'-]{2,100}$" , message = "¿De verdad su nombre tienen numeros D:?")
	private String nombre;

	@Column(name = "email")
	@NotEmpty(message = "Debes indicar tu email")
	@Email(message = "Debes indicar un email válido")
	@Size(max = 100, message = "Este correo electrónico es demasiado largo para almacenarlo en nuestro servidor, disculpe las molestias.")
	private String email;

	@Column(name = "telefono")
	@Size(max = 15, message = "Debe introducir un numero de telefono válido o dejar este campo en blanco")
	private String telefono;

	@Column(name = "password")
	@Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$" , message = "Al menos 8 carácteres de los cuales al menos 1 mayúscula, 1 minúscula y 1 número")
	private String password;

	@ManyToOne
	@JoinColumn(name = "centro_usuario", foreignKey = @ForeignKey(name = "fk_centro_usuario_idx"))
	private Centro centroUsuario;

	@ManyToOne
	@JoinColumn(name = "rol_usuario", foreignKey = @ForeignKey(name = "fk_rol_usuario_idx"))
	private Rol rolUsuario;

	@ManyToOne
	@JoinColumn(name = "idioma_usuario", foreignKey = @ForeignKey(name = "fk_idioma_usuario_idx"))
	private Idioma idiomaUsuario;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Centro getCentroUsuario() {
		return centroUsuario;
	}

	public void setCentroUsuario(Centro centroUsuario) {
		this.centroUsuario = centroUsuario;
	}

	public Rol getRolUsuario() {
		return rolUsuario;
	}

	public void setRolUsuario(Rol rolUsuario) {
		this.rolUsuario = rolUsuario;
	}

	public Idioma getIdiomaUsuario() {
		return idiomaUsuario;
	}

	public void setIdiomaUsuario(Idioma idiomaUsuario) {
		this.idiomaUsuario = idiomaUsuario;
	}

	public Usuario() {
	}

	public Usuario(String nombre, String email, String telefono, String password, Centro centroUsuario, Rol rolUsuario,
			Idioma idiomaUsuario) {
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.password = password;
		this.centroUsuario = centroUsuario;
		this.rolUsuario = rolUsuario;
		this.idiomaUsuario = idiomaUsuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centroUsuario == null) ? 0 : centroUsuario.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idUsuario;
		result = prime * result + ((idiomaUsuario == null) ? 0 : idiomaUsuario.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((rolUsuario == null) ? 0 : rolUsuario.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Usuario other = (Usuario) obj;
		if (centroUsuario == null) {
			if (other.centroUsuario != null)
				return false;
		} else if (!centroUsuario.equals(other.centroUsuario))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		if (idiomaUsuario == null) {
			if (other.idiomaUsuario != null)
				return false;
		} else if (!idiomaUsuario.equals(other.idiomaUsuario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (rolUsuario == null) {
			if (other.rolUsuario != null)
				return false;
		} else if (!rolUsuario.equals(other.rolUsuario))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
				+ ", password=" + password + ", centroUsuario=" + centroUsuario + ", rolUsuario=" + rolUsuario
				+ ", idiomaUsuario=" + idiomaUsuario + "]";
	}

	
}
