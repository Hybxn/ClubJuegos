package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.Rol;
import com.clubjuegos.services.RolService;

@Controller
public class RolController {
	
	@Autowired
	private RolService serv;

	@GetMapping("/management/roles")
	public String listaRolesManagement(Model model) {
		
		model.addAttribute("roles", serv.findAll());
		
		return "Management/Listas/ListaRoles";
	}
	
	@GetMapping("/management/roles/new")
	public String formularioCrearRol(Model model) {
		
		model.addAttribute("rol", new Rol());
		
		return "Management/Formularios/RolesForm";
	}
	
	@PostMapping("/management/roles/new/submit")
	public String crearRol(Rol rol, Model model) {
		
		serv.add(rol);
		
		return "redirect:/management/roles";
	}
	
	@GetMapping("/management/roles/edit/{id}")
	public String editaRol(@PathVariable("id") int id, Model model) {
		
		Rol rol = serv.findById(id);
		
		if (rol != null) {
			
			model.addAttribute("rol", rol);
			return "Management/Formularios/RolesForm";
		}
		
		return "redirect:/management/roles";
	}
	
	@GetMapping("management/roles/delete/{id}")
	public String borraRol(@PathVariable("id") int id, Model model) {
		
		Rol rol = serv.findById(id);
		
		if (rol != null) {
			 serv.deleteByElement(rol);
		}
		
		return "redirect:/management/roles";
	}

}
