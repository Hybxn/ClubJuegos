package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clubjuegos.models.Partida;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.EstadosPartidaService;
import com.clubjuegos.services.PartidaService;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.utils.Utilidades;

@Controller
public class PartidaController {

	@Autowired
	private PartidaService serv;
	@Autowired
	private UsuarioService usuarios;
	@Autowired
	private EstadosPartidaService estados;

	@GetMapping("/jugador/partidas")
	public String listaPartidas(Model model) {

		model.addAttribute("listaPartidas", serv.findAll());

		return "/Management/Listas/listaPartidas";
	}

	@GetMapping("/jugador/partidas/buscar")
	public String buscarPartida(Model model) {

		Usuario conectado = Utilidades.dameConectado(usuarios);
		
		model.addAttribute("listaPartidas", serv.buscarPartida(conectado));

		return "/Management/Listas/listaPartidas";
	}

	@GetMapping("/jugador/organizador")
	public String buscarPorOrganizador(Model model) {
		
		Usuario conectado = Utilidades.dameConectado(usuarios);
		
		if (serv.misPartidasOrganizadas(conectado).isEmpty()) {
			return "redirect:/miPanel?noOrganizada";
		}
		model.addAttribute("listaPartidas", serv.misPartidasOrganizadas(conectado));
		return "/Management/Listas/listaPartidas";
	}

	@GetMapping("jugador/organizador/terminar/{id}")
	public String buscarPorOrganizadorTerminada(Model model, @PathVariable int id) {
		Partida partida = serv.findById(id);
		partida.setEstadoPartida(estados.findById(2));
		serv.add(partida);
		return "redirect:/miPanel?termianda";
	}


	@GetMapping("/jugador/historial")
	public String historial(Model model) {

		Usuario conectado = Utilidades.dameConectado(usuarios);
		
		if (serv.historial(conectado).isEmpty()) {
			return "redirect:/miPanel?noJugado";
		}
		model.addAttribute("listaPartidas", serv.historial(conectado));
		return "/Management/Listas/listaPartidas";
	}
}
