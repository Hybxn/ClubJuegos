package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.EstadosIncidencia;
import com.clubjuegos.services.EstadosIncidenciaService;

@Controller
public class EstadosIncidenciaController {
	
	@Autowired
	private EstadosIncidenciaService servicio;
	
	@GetMapping("/management/estadosIncidencia")
	public String listaEstadosIncidencia(Model model) {
		
		model.addAttribute("estadosIncidencia", servicio.findAll());
		
		return "Management/Listas/ListaEstadosIncidencia";
	}
	
	@GetMapping("/management/estadosIncidencia/new")
	public String formularioCrearEstadosIncidencia(Model model) {
		
		model.addAttribute("estadosIncidencia", new EstadosIncidencia());
		
		return "Management/Formularios/EstadosIncidenciaForm";
	}
	
	@PostMapping("/management/estadosIncidencia/new/submit")
	public String crearEstadosIncidencia(EstadosIncidencia estadosIncidencia, Model model) {
		
		servicio.add(estadosIncidencia);
		
		return "redirect:/management/estadosIncidencia";
	}
	
	@GetMapping("/management/estadosIncidencia/edit/{id}")
	public String editaEstadosIncidencia(@PathVariable("id") int id, Model model) {
		
		EstadosIncidencia estadosIncidencia = servicio.findById(id);
		
		if (estadosIncidencia != null) {
			
			model.addAttribute("estadosIncidencia", estadosIncidencia);
			return "Management/Formularios/EstadosIncidenciaForm";
		}
		
		return "redirect:/management/estadosIncidencia";
	}
	
	@GetMapping("management/estadosIncidencia/delete/{id}")
	public String borraEstadosIncidencia(@PathVariable("id") int id, Model model) {
		
		EstadosIncidencia estadosIncidencia = servicio.findById(id);
		
		if (estadosIncidencia != null) {
			 servicio.deleteByElement(estadosIncidencia);
		}
		
		return "redirect:/management/estadosIncidencia";
	}


}
