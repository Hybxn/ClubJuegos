package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.ValoracionJugador;
import com.clubjuegos.services.EmparejamientoService;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.services.ValoracionActividadService;
import com.clubjuegos.services.ValoracionJugadorService;
import com.clubjuegos.utils.Utilidades;

@Controller
public class ValoracionJugadorController {

	@Autowired
	private ValoracionJugadorService serv;
	@Autowired
	private ValoracionActividadService otrasValoraciones;
	@Autowired
	private EmparejamientoService emparejamientos;
	@Autowired
	private UsuarioService usuarios;

	@GetMapping("/jugador/valoracion/jugador/{id}")
	public String nuevaValoracion(Model model, @PathVariable int id) {
		
		if (serv.existeValoracion(emparejamientos.findById(id), Utilidades.dameConectado(usuarios)) == true) {
			return "redirect:/jugador/historial?valorar&jugadorValorado";
		}
		model.addAttribute("valoracion", new ValoracionJugador(emparejamientos.findById(id), Utilidades.dameConectado(usuarios)));
		return "/Management/Formularios/ValoracionJugador";
	}

	@PostMapping("/jugador/valoracion/jugador/submit")
	public String nuevaValoracionSubmit(Model model, ValoracionJugador valoracion) {

		serv.add(valoracion);
		return "redirect:/miPanel?valorado";
	}
	@GetMapping("/management/top/jugadores")
	public String topJugadores(Model model) {

		model.addAttribute("organizadores", otrasValoraciones.topOrganizadores(usuarios));
		model.addAttribute("jugadores", serv.topJugadores(usuarios));
		
		return "Management/Listas/TopJugadores";
	}

}
