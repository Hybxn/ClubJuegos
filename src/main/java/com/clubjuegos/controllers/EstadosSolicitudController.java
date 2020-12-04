package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.EstadosSolicitud;
import com.clubjuegos.services.EstadosSolicitudService;

@Controller
public class EstadosSolicitudController {
	
	@Autowired
	private EstadosSolicitudService servicio;

	@GetMapping("/management/estadosSolicitud")
	public String listaEstadosSolicitud(Model model) {
		
		model.addAttribute("estadosSolicitud", servicio.findAll());
		
		return "Management/Listas/ListaEstadosSolicitud";
	}
	
	@GetMapping("/management/estadosSolicitud/new")
	public String formularioCrearEstadosSolicitud(Model model) {
		
		model.addAttribute("estadosSolicitud", new EstadosSolicitud());
		
		return "Management/Formularios/EstadosSolicitudForm";
	}
	
	@PostMapping("/management/estadosSolicitud/new/submit")
	public String crearEstadosSolicitud(EstadosSolicitud estadosSolicitud, Model model) {
		
		servicio.add(estadosSolicitud);
		
		return "redirect:/management/estadosSolicitud";
	}
	
	@GetMapping("/management/estadosSolicitud/edit/{id}")
	public String editaEstadosSolicitud(@PathVariable("id") int id, Model model) {
		
		EstadosSolicitud estadosSolicitud = servicio.findById(id);
		
		if (estadosSolicitud != null) {
			
			model.addAttribute("estadosSolicitud", estadosSolicitud);
			return "Management/Formularios/EstadosSolicitudForm";
		}
		
		return "redirect:/management/estadosSolicitud";
	}
	
	@GetMapping("management/estadosSolicitud/delete/{id}")
	public String borraEstadosSolicitud(@PathVariable("id") int id, Model model) {
		
		EstadosSolicitud estadosSolicitud = servicio.findById(id);
		
		if (estadosSolicitud != null) {
			 servicio.deleteByElement(estadosSolicitud);
		}
		
		return "redirect:/management/estadosSolicitud";
	}

	
	
	
	
	
}
