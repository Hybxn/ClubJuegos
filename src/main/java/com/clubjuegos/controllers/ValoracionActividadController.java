package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.ValoracionActividad;
import com.clubjuegos.services.JuegoService;
import com.clubjuegos.services.PartidaService;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.services.ValoracionActividadService;
import com.clubjuegos.utils.Utilidades;

@Controller
public class ValoracionActividadController {

	@Autowired
	private ValoracionActividadService serv;
	@Autowired
	private PartidaService partidas;
	@Autowired
	private UsuarioService usuarios;
	@Autowired
	private JuegoService juegos;
	
	@GetMapping("/jugador/valoracion/{id}")
	public String nuevaValoracion(Model model, @PathVariable int id) {

		if (serv.existeValoracion(partidas.findById(id), Utilidades.dameConectado(usuarios))) {
			return "redirect:/jugador/historial?valorar&yaValorada";
		}
		
		model.addAttribute("valoracion", new ValoracionActividad(partidas.findById(id), Utilidades.dameConectado(usuarios)));
		return "/Management/Formularios/ValoracionActividad";
	}
	
	@PostMapping("/jugador/valoracion/submit")
	public String nuevaValoracionSubmit(Model model, ValoracionActividad valoracion) {
		
		serv.add(valoracion);
		return "redirect:/miPanel?valorado";
	}

	@GetMapping("/management/top/juegos")
	public String topJuegos(Model model) {
		
		model.addAttribute("juegos", serv.topJuegos(juegos));
		
		return "Management/Listas/TopJuegos";
	}
//	@GetMapping("/management/top/jugadores")
//	public String topJugadores(Model model) {
//		
//		model.addAttribute("organizadores", serv.topOrganizadores(usuarios));
//		
//		return "Management/Listas/TopJugadores";
//	}

}
