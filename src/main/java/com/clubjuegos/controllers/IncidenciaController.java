package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.Incidencia;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.CorreoService;
import com.clubjuegos.services.EstadosIncidenciaService;
import com.clubjuegos.services.IncidenciaService;
import com.clubjuegos.services.PartidaService;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.utils.Utilidades;

@Controller
public class IncidenciaController {

	@Autowired
	private IncidenciaService serv;
	@Autowired
	private PartidaService partidas;
	@Autowired
	private EstadosIncidenciaService estados;
	@Autowired
	private UsuarioService usuarios;
	@Autowired
	private CorreoService mail;
	
	@GetMapping("/management/incidencias")
	public String listaIncidencias(Model model) {
		
		Usuario conectado = Utilidades.dameConectado(usuarios);
		
		model.addAttribute("ListaIncidencias", serv.incidenciasMiCentro(conectado));
		
		return "Management/Listas/ListaIncidencias";
	}

	@GetMapping("/jugador/partidas/incidencias/new/{id}")
	public String formularioCrearIncidencias(Model model, @PathVariable int id) {

		model.addAttribute("incidencia", new Incidencia(partidas.findById(id), estados.findById(1)));
		
		return "Management/Formularios/IncidenciaForm";
	}
	
	@PostMapping("/jugador/partidas/incidencias/new/submit")
	public String crearIncidencia(Incidencia incidencia, Model model) {
		
		serv.add(incidencia);
		
		return "redirect:/miPanel?IncidenciaEnviada";
	}
	
	@GetMapping("/management/incidencias/edit/{id}")
	public String editaIncidencia(@PathVariable("id") int id, Model model) {
		
		Incidencia incidencia = serv.findById(id);
		
		if (incidencia != null) {
			
			model.addAttribute("incidencia", incidencia);
			model.addAttribute("partidas", partidas.findById(incidencia.getPartidaIncidencia().getId()));
			model.addAttribute("estados", estados.findAll());
			return "Management/Formularios/IncidenciaForm";
		}
		
		return "redirect:/management/incidencias";
	}
	
	@GetMapping("management/incidencias/delete/{id}")
	public String borraIncidencia(@PathVariable("id") int id, Model model) {
		
		Incidencia incidencia = serv.findById(id);
		
		if (incidencia != null) {
			if (incidencia.getEstadoIncidencia().getIdEstadoIncidencia() == 1) {
				incidencia.setEstadoIncidencia(estados.findById(2));
				serv.add(incidencia);
				mail.incidenciaRevisada(usuarios.findCoordinador(incidencia.getPartidaIncidencia().getSolicitudPartida().getJuegoSolicitud().getCentroJuego().getIdCentro()).getEmail(), incidencia);				
			}else {
				incidencia.setEstadoIncidencia(estados.findById(1));
				serv.add(incidencia);
			}
		}
		
		return "redirect:/management/incidencias";
	}
}
