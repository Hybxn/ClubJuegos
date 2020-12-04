package com.clubjuegos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Incidencia;

@Service
public class CorreoService {

	// Clase servicio que se usa para enviar emails de manera automatica.

	@Autowired
	private JavaMailSender javaMailSender;// Objeto predefinido para enviar los emails con Java desde Spring.

	public void bienvenida(String to, int idioma) {// Método que enviará un email al usuario que se registre en la
													// aplicación.

		SimpleMailMessage mail = new SimpleMailMessage();// Creamos el objeto que usaremos para enviar un email de texto
															// plano.

		if (idioma == 1) {// Si el usuario ha marcado español como idioma preferido, se le enivará un
							// saludo en español.

			mail.setFrom("ivan.mgarcia125@gmail.com");// Se envian desde mi email personal.
			mail.setTo(to);// La variable "to" es el email del usuario que se acaba de registrar.

			// Texto típico en un mensaje de bienvenida en el asunto y en el contenido del
			// email.
			mail.setSubject("¡Hola!");
			mail.setText("Bienvenido al club de juegos de mesa, esperamos que disfrutes al máximo tus partidas :D");

		} else {// Si el usuario ha marcado ingles como idioma preferido, se le enviará el
				// saludo en ingles.

			// Se siguen los mismos pasos que en la anterior condición
			mail.setFrom("ivan.mgarcia125@gmail.com");
			mail.setTo(to);
			mail.setSubject("¡Hi!");
			mail.setText("Welcome to the Boardgames Club, we hope you enjoy your future games! :D");

		}

		javaMailSender.send(mail);// Utilizamos el objeto autocableado en la clase para enviar el email.
	}

	public void jugadorUnido(String organizador, String jugador) {// Método que se utiliza para avisar a un coordinador
																	// que un jugador se ha unido a su partida.

		// Creamos el objeto que usaremos para enviar un email de texto plano.
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setFrom("ivan.mgarcia125@gmail.com");		
		//Como el propio nombre indica, la variable organizador es un String que contienen el email del organizador.
		mail.setTo(organizador);
		
		//Contenido del email
		mail.setSubject("Jugador unido");
		mail.setText("El jugador " + jugador + " se ha unido a tu partida.");

		//Se envia el email.
		javaMailSender.send(mail);

	}

	//Método que se utiliza cuando un jugador abandona la partida.
	public void jugadorAbandona(String organizador, String jugador) {
		SimpleMailMessage mail = new SimpleMailMessage();// Creamos el objeto que usaremos para enviar un email de texto
															// plano.

		mail.setFrom("ivan.mgarcia125@gmail.com");
		//"organizador" es el String que contiene el email del usuario organizador de la partida.
		mail.setTo(organizador);
		
		//Contenido del email
		mail.setSubject("Jugador unido");
		mail.setText("El jugador " + jugador + " ha abandonado tu partida.");

		//Se envia el email.
		javaMailSender.send(mail);

	}

	//Método que se utiliza cuando un Responsable de Mantenimiento marca una incidencia como revisada.
	public void incidenciaRevisada(String coordinador, Incidencia incidencia) {

		//Instanciamos el objeto que usaremos para enviar un email como texto plano
		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setFrom("ivan.mgarcia125@gmail.com");
		//"Coordinador" es el String que contiene el email del coordinador del centro.
		mail.setTo(coordinador);
		//Se le informa al coordinador de la incidencia que se ha resulto ahora mismo.
		mail.setSubject("Jugador unido");
		mail.setText("La incidencia #" + incidencia.getIdIncidencias() + " con la descripción: "
				+ incidencia.getDescrIncidencia() + ". Ha sido revisada por un Responsable de Mantenimiento del Club.");

		//Se envia el email.
		javaMailSender.send(mail);

	}

}
