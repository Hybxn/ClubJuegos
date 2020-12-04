package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.EstadosPartida;
import com.clubjuegos.services.EstadosPartidaService;

@Controller
public class EstadosPartidaController {
	
	@Autowired
	private EstadosPartidaService servicio;
	
	@GetMapping("/management/estadosPartida")
	public String listaEstadosPartida(Model model) {
		
		model.addAttribute("estadosPartida", servicio.findAll());
		
		return "Management/Listas/ListaEstadosPartida";
	}
	
	@GetMapping("/management/estadosPartida/new")
	public String formularioCrearEstadosPartida(Model model) {
		
		model.addAttribute("estadosPartida", new EstadosPartida());
		
		return "Management/Formularios/EstadosPartidaForm";
	}
	
	@PostMapping("/management/estadosPartida/new/submit")
	public String crearEstadosPartida(EstadosPartida estadosPartida, Model model) {
		
		servicio.add(estadosPartida);
		
		return "redirect:/management/estadosPartida";
	}
	
	@GetMapping("/management/estadosPartida/edit/{id}")
	public String editaEstadosPartida(@PathVariable("id") int id, Model model) {
		
		EstadosPartida estadosPartida = servicio.findById(id);
		
		if (estadosPartida != null) {
			
			model.addAttribute("estadosPartida", estadosPartida);
			return "Management/Formularios/EstadosPartidaForm";
		}
		
		return "redirect:/management/estadosPartida";
	}
	
	@GetMapping("management/estadosPartida/delete/{id}")
	public String borraEstadosPartida(@PathVariable("id") int id, Model model) {
		
		EstadosPartida estadosPartida = servicio.findById(id);
		
		if (estadosPartida != null) {
			 servicio.deleteByElement(estadosPartida);
		}
		
		return "redirect:/management/estadosPartida";
	}

	

	
}
