package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.Centro;
import com.clubjuegos.services.CentroService;

@Controller
public class CentroController {
	
	@Autowired
	private CentroService servicio;
	
	@GetMapping("/centros")
	public String listaCentros(Model model) {
		
		model.addAttribute("listaCentros", servicio.findAll());
		
		return "NuestrosCentros";
	}
	
	@GetMapping("/management/centros")
	public String listaCentrosManagement(Model model) {
		
		model.addAttribute("centros", servicio.findAll());
		
		return "Management/Listas/ListaCentros";
	}

	@GetMapping("/management/centros/new")
	public String formularioCrearCentro(Model model) {
		
		model.addAttribute("centro", new Centro());
		
		return "Management/Formularios/CentrosForm";
	}
	
	@PostMapping("/management/centros/new/submit")
	public String crearCentro(Centro centro, Model model) {
		
		servicio.add(centro);
		
		return "redirect:/management/centros";
	}
	
	@GetMapping("/management/centros/edit/{id}")
	public String editaCentro(@PathVariable("id") int id, Model model) {
		
		Centro centro = servicio.findById(id);
		
		if (centro != null) {
			
			model.addAttribute("centro", centro);
			return "Management/Formularios/CentrosForm";
		}
		
		return "redirect:/management/centros";
	}
	
	@GetMapping("management/centros/delete/{id}")
	public String borraCentro(@PathVariable("id") int id, Model model) {
		
		Centro centro = servicio.findById(id);
		
		if (centro != null) {
			 servicio.deleteByElement(centro);
		}
		
		return "redirect:/management/centros";
	}
}
