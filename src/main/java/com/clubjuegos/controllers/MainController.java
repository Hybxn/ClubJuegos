package com.clubjuegos.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.utils.Utilidades;


@Controller
public class MainController {

	@GetMapping("/")
	public String landing(Map<String, Object> model) {

		return "LandingPage";
	}

	@GetMapping("/cambiarDatos")
	public String cambiarDatos(Model model) {

		return "CambiarDatos";
	}

	@Autowired
	private UsuarioService usuarios;

	@GetMapping("/miPanel")
	public String panel(Model model) {
		Usuario conectado = Utilidades.dameConectado(usuarios);

		if (conectado != null) {
			if (conectado.getRolUsuario().getNombreRol().equals("Coordinador")) {
				return "PanelCoordinador";
			}
			if (conectado.getRolUsuario().getNombreRol().equals("Responsable de Mantenimiento")) {
				return "PanelResponsable";
			}
			if (conectado.getRolUsuario().getNombreRol().equals("Jugador")) {
				return "PanelJugador";
			}
		}
		return "redirect:/";
	}
	
	@GetMapping("/panel")
	public String miPanel() {
		Usuario conectado = Utilidades.dameConectado(usuarios);
		if (conectado.getIdiomaUsuario().getIdIdiomas() == 1) {
			return "redirect:/miPanel?lang=es";			
		}else {
			return "redirect:/miPanel?lang=en";			
		}
	}
}
