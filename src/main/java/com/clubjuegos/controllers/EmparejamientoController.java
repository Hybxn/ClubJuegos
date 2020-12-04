package com.clubjuegos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clubjuegos.models.Emparejamiento;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.CorreoService;
import com.clubjuegos.services.EmparejamientoService;
import com.clubjuegos.services.PartidaService;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.utils.Utilidades;

@Controller
public class EmparejamientoController {

	@Autowired
	private EmparejamientoService serv;
	@Autowired
	private PartidaService partidas;
	@Autowired
	private UsuarioService usuarios;
	@Autowired
	private CorreoService mail;

	@GetMapping("/emparejamientos")
	public String listaEmparejamientos(Model model) {
		model.addAttribute("listaEmparejamientos", serv.findAll());
		return "/Management/Listas/ListaEmparejamientos";
	}

	@GetMapping("/jugador/emparejamiento/unirse/{id}")
	public String nuevoEmparejamiento(Model model, @PathVariable int id) {

		Usuario conectado = Utilidades.dameConectado(usuarios);

		if (conectado != null) {
			List<Emparejamiento> emparejamientos = serv.findAll();
			for (Emparejamiento emparejamiento : emparejamientos) {
				if (emparejamiento.getPartidaEmparejada() == id
						&& emparejamiento.getUsuarioEmparejado().equalsIgnoreCase(conectado.getEmail())) {
					return "redirect:/miPanel?yaUnido";
				}
			}
			serv.add(new Emparejamiento(partidas.findById(id), conectado));			
			mail.jugadorUnido(partidas.findById(id).getSolicitudPartida().getUsuarioOrganizador().getEmail(), conectado.getEmail());			
			return "redirect:/miPanel?emparejado";
		}
		return "redirect:/miPanel?noEmparejado";
	}

	@GetMapping("/jugador/emparejamiento")
	public String empajamientosActuales(Model model) {

		Usuario conectado = Utilidades.dameConectado(usuarios);
		if (serv.misEmparejamientos(conectado).isEmpty()) {
			
			return "redirect:/jugador/partidas/buscar?unirse&noEmparejado";			
		}
		
		model.addAttribute("listaPartidas", serv.misEmparejamientos(conectado));
		return "Management/Listas/listaPartidas";
		
	}

	@GetMapping("/jugador/emparejamiento/salir/{id}")
	public String borrarEmparejamiento(Model model, @PathVariable int id) {
		Usuario conectado = Utilidades.dameConectado(usuarios);
		
		serv.abandonarPartida(conectado, id);
		mail.jugadorAbandona(partidas.findById(id).getSolicitudPartida().getUsuarioOrganizador().getEmail(), conectado.getEmail());
		return "redirect:/miPanel?abandonada";
	}


	@GetMapping("/jugador/historial/otrosJugadores/{id}")
	public String listaEmparejamientos(Model model, @PathVariable int id) {
		model.addAttribute("listaEmparejamientos", serv.findByPartida(id));
		return "/Management/Listas/ListaEmparejamientos";
	}
}
