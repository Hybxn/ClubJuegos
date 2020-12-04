package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.Idioma;
import com.clubjuegos.services.IdiomaService;

@Controller
public class IdiomaController {
	
	@Autowired
	public IdiomaService serv;

	@GetMapping("/management/idiomas")
	public String listaidiomas(Model model) {
		
		model.addAttribute("idiomas", serv.findAll());
		
		return "Management/Listas/ListaIdiomas";
	}
	
	@GetMapping("/management/idiomas/new")
	public String formularioCrearIdioma(Model model) {
		
		model.addAttribute("idioma", new Idioma());
		
		return "Management/Formularios/IdiomasForm";
	}
	
	@PostMapping("/management/idiomas/new/submit")
	public String crearIdioma(Idioma idioma, Model model) {
		
		serv.add(idioma);
		
		return "redirect:/management/idiomas";
	}
	
	@GetMapping("/management/idiomas/edit/{id}")
	public String editaIdioma(@PathVariable("id") int id, Model model) {
		
		Idioma idioma = serv.findById(id);
		
		if (idioma != null) {
			
			model.addAttribute("idioma", idioma);
			return "Management/Formularios/IdiomasForm";
		}
		
		return "redirect:/management/idiomas";
	}
	
	@GetMapping("management/idiomas/delete/{id}")
	public String borraIdioma(@PathVariable("id") int id, Model model) {
		
		Idioma idioma = serv.findById(id);
		
		if (idioma != null) {
			 serv.deleteByElement(idioma);
		}
		
		return "redirect:/management/idiomas";
	}

	
	
	
	

}
