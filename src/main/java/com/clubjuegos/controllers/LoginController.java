package com.clubjuegos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.CentroService;
import com.clubjuegos.services.CorreoService;
import com.clubjuegos.services.IdiomaService;
import com.clubjuegos.services.RolService;
import com.clubjuegos.services.UsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	private CorreoService correo;
	@Autowired
	private UsuarioService usuarios;
	@Autowired
	private RolService roles;
	@Autowired
	private CentroService centros;
	@Autowired
	private IdiomaService idiomas;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/login")	
	public String login(Model model) {

		return "Login";
	}

	@GetMapping("/registro")
	public String registro(Model model) {

		model.addAttribute("usuario", new Usuario());
		model.addAttribute("centros", centros.findAll());
		model.addAttribute("idiomas", idiomas.findAll());
		model.addAttribute("roles", roles.findById(1));
		
		return "Registro";
	}

	@PostMapping("/registro/submit")
	public String registroSubmit(@ModelAttribute @Valid  Usuario usuario, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {

			model.addAttribute("centros", centros.findAll());
			model.addAttribute("idiomas", idiomas.findAll());
			model.addAttribute("roles", roles.findById(1));			

			return "Registro";
			
		} else {

			for (Usuario usu : usuarios.findAll()) {
				
				if (usu.getEmail().equalsIgnoreCase(usuario.getEmail())) {
					
					return "redirect:/registro?emailusado";					
				}
			}
			
			if (usuario.getCentroUsuario() == null || usuario.getIdiomaUsuario() == null) {
				
				return "redirect:/registro?noCentroIdioma";
			}

			correo.bienvenida(usuario.getEmail(),usuario.getIdiomaUsuario().getIdIdiomas());
			bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
			String password = bCryptPasswordEncoder.encode(usuario.getPassword());

			usuario.setPassword(password);
			usuario.setRolUsuario(roles.findById(1));
			usuarios.add(usuario);

			return "redirect:/login";
		}
	}
}
