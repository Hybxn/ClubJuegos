package com.clubjuegos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.CentroService;
import com.clubjuegos.services.IdiomaService;
import com.clubjuegos.services.RolService;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.utils.Utilidades;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService servicio;
	@Autowired
	private CentroService centros;
	@Autowired
	private IdiomaService idiomas;
	@Autowired
	private RolService roles;

	@GetMapping("/management/usuarios")
	public String listaUsuarioManagement(Model model) {

		Usuario conectado = Utilidades.dameConectado(servicio);
		model.addAttribute("listaUsuarios", servicio.findByCentro(conectado.getCentroUsuario().getIdCentro()));
		return "/Management/Listas/ListaUsuarios";
	}

	@GetMapping({ "/management/usuarios/new" })
	public String nuevoUsuario(Model model) {

		model.addAttribute("usuario", new Usuario());
		model.addAttribute("centros", centros.findAll());
		model.addAttribute("idiomas", idiomas.findAll());
		model.addAttribute("roles", roles.findAll());

		return "/Management/Formularios/UsuarioForm";
	}

	@PostMapping("/management/usuarios/new/submit")
	public String nuevoUsuarioSubmit(Usuario usuario, Model model) {

		if (usuario.getPassword() == null) {
			usuario.setPassword(servicio.findById(usuario.getIdUsuario()).getPassword());
		}
		servicio.add(usuario);

		return "redirect:/management/usuarios";
	}

	@GetMapping("/management/usuarios/edit/{id}")
	public String editaJuego(@PathVariable("id") int id, Model model) {

		Usuario usuario = servicio.findById(id);

		if (usuario != null) {
			model.addAttribute("usuario", usuario);
			model.addAttribute("centros", centros.findAll());
			model.addAttribute("idiomas", idiomas.findAll());
			model.addAttribute("roles", roles.findAll());
			return "/Management/Formularios/UsuarioForm";
		}

		return "redirect:/management/usuarios";
	}

	@GetMapping("/management/usuarios/delete/{id}")
	public String borraJuego(@PathVariable("id") int id, Model model) {

		Usuario usu = servicio.findById(id);

		if (usu != null) {

			servicio.deleteById(usu.getIdUsuario());
		}

		return "redirect:/management/usuarios";
	}

	@GetMapping("/miPerfil/edit")
	public String miPerfilEdit(Model model) {
		Usuario conectado = Utilidades.dameConectado(servicio);

		if (conectado != null) {

			model.addAttribute("usuario", conectado);
			model.addAttribute("centros", centros.findAll());
			model.addAttribute("roles", roles.findAll());
			model.addAttribute("idiomas", idiomas.findAll());

			return "CambiarDatos";
		}
		return "redirect:/panel";
	}

	@PostMapping("/miPerfil/edit/submit")
	public String miPerfilEditSubmit(@ModelAttribute @Valid Usuario usuario, BindingResult br, Model model,
			@RequestParam("nuevaPasswdConf") String nuevaPasswdConf, @RequestParam("nuevaPasswd") String nuevaPasswd) {

		if (br.hasErrors()) {
			Usuario conectado = Utilidades.dameConectado(servicio);

			if (conectado != null) {

				model.addAttribute("centros", centros.findAll());
				model.addAttribute("roles", roles.findAll());
				model.addAttribute("idiomas", idiomas.findAll());

				return "CambiarDatos";
			}
		}

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(4);
		usuario.setRolUsuario(servicio.findById(usuario.getIdUsuario()).getRolUsuario());

		if (nuevaPasswd.equals("")
				&& bcrypt.matches(usuario.getPassword(), servicio.findById(usuario.getIdUsuario()).getPassword())) {

			usuario.setPassword(servicio.findById(usuario.getIdUsuario()).getPassword());
			servicio.add(usuario);

			return "redirect:/panel?editado";

		} else {

			if (bcrypt.matches(usuario.getPassword(), servicio.findById(usuario.getIdUsuario()).getPassword())
					&& nuevaPasswd.equals(nuevaPasswdConf)) {

				usuario.setPassword(bcrypt.encode(nuevaPasswdConf));
				servicio.add(usuario);

				return "redirect:/panel?editado";
			}
		}

		return "redirect:/miPerfil/edit?noEditado";
	}

}
