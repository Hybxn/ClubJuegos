package com.clubjuegos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.Juego;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.CentroService;
import com.clubjuegos.services.IdiomaService;
import com.clubjuegos.services.JuegoService;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.utils.Utilidades;

@Controller
public class JuegoController {

	@Autowired
	private JuegoService serv;
	@Autowired
	private CentroService centroserv;
	@Autowired
	private IdiomaService idiomaserv;
	@Autowired
	private UsuarioService usuarios;

	@GetMapping("/juegos")
	public String listaJuegos(Model model) {

		model.addAttribute("listaJuegos", serv.findAll());

		return "NuestrosJuegos";
	}

	@GetMapping({ "/coordinador/juegos/new", "/responsable/juegos/new", "/management/juegos/new" })
	public String nuevoJuego(Model model) {

		model.addAttribute("juego", new Juego());
		model.addAttribute("centros", centroserv.findAll());
		model.addAttribute("idiomas", idiomaserv.findAll());

		return "/Management/Formularios/JuegoForm";
	}

	@PostMapping({ "management/juegos/new/submit", "management/juegos/new/submit" })
	public String nuevoJuegoSubmit(Juego juego, Model model) {

		serv.add(juego);

		return "redirect:/management/juegos";
	}

	@GetMapping("/management/juegos")
	public String listaJuegosManagement(Model model) {		
		Usuario conectado = Utilidades.dameConectado(usuarios);
		model.addAttribute("listaJuegos", serv.findByCentro(conectado.getCentroUsuario().getIdCentro()));

		return "/Management/Listas/ListaJuegos";
	}

	@GetMapping("/management/juegos/delete/{id}")
	public String borraJuego(@PathVariable("id") int id, Model model) {

		Juego juego = serv.findById(id);

		if (juego != null) {

			serv.deleteById(juego.getIdJuegos());
		}

		return "redirect:/management/juegos";
	}

	@GetMapping("/management/juegos/edit/{id}")
	public String editaJuego(@PathVariable("id") int id, Model model) {

		Juego juego = serv.findById(id);

		if (juego != null) {
			model.addAttribute("juego", juego);
			model.addAttribute("centros", centroserv.findAll());
			model.addAttribute("idiomas", idiomaserv.findAll());
			return "/Management/Formularios/JuegoForm";
		}

		return "redirect:/management/juegos";
	}
}
