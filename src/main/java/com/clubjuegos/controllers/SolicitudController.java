package com.clubjuegos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.clubjuegos.models.Partida;
import com.clubjuegos.models.Solicitud;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.EstadosPartidaService;
import com.clubjuegos.services.EstadosSolicitudService;
import com.clubjuegos.services.JuegoService;
import com.clubjuegos.services.PartidaService;
import com.clubjuegos.services.SolicitudService;
import com.clubjuegos.services.UsuarioService;
import com.clubjuegos.utils.Utilidades;

@Controller
public class SolicitudController {

	@Autowired
	private SolicitudService servicio;
	@Autowired
	private UsuarioService usuarios;
	@Autowired
	private JuegoService juegos;
	@Autowired
	private EstadosSolicitudService estados;
	@Autowired
	private PartidaService partidas;
	@Autowired
	private EstadosPartidaService estadospartidas;
	
	@GetMapping("/jugador/solicitudes")
	public String listaCentros(Model model) {
		Usuario conectado = Utilidades.dameConectado(usuarios);
		
		model.addAttribute("listaSolicitudes", servicio.findByUsuario(conectado.getIdUsuario()));
		
		return "/Management/Listas/listaSolicitudesAlpha";
	}
	
	@GetMapping("/jugador/solicitudes/new")
	public String formularioCrearSolicitud(Model model) {
		
		model.addAttribute("solicitud", new Solicitud());
		model.addAttribute("usuario", usuarios.findById(Utilidades.dameConectado(usuarios).getIdUsuario()));
		model.addAttribute("juegos", juegos.findByCentro(Utilidades.dameConectado(usuarios).getCentroUsuario().getIdCentro()));
		model.addAttribute("estados", estados.findAll());
		
		return "/Management/Formularios/SolicitudForm";
		
	}
	
	@PostMapping("/jugador/solicitudes/new/submit")
	public String crearSolicitud(Solicitud solicitud, Model model) {
		
		solicitud.setEstadoSolicitud(estados.findById(1));
		
		servicio.add(solicitud);
		
		return "redirect:/jugador/solicitudes";
		
	}
	
	@GetMapping("/management/solicitudes")
	public String muestraSolicitudesPendientes(Model model) {

		Usuario conectado = Utilidades.dameConectado(usuarios);
		
		List<Integer> listaJuegos = juegos.findCodigoByCentro(conectado.getCentroUsuario().getIdCentro());		
		model.addAttribute("solicitudes", servicio.findByCenter(listaJuegos, 1));
		
		return "/Management/Listas/ListaSolicitudes";
		
	}
	
	@GetMapping("/management/solicitudes/accept/{id}")
	public String aceptaSolicitud(@PathVariable ("id") int id , Model model) {
		
		Solicitud solicitud = servicio.findById(id);
		
		if (solicitud != null) {
			solicitud.setEstadoSolicitud(estados.findById(2));
			servicio.add(solicitud);
			partidas.add(new Partida(solicitud, estadospartidas.findById(3)));
		}
		
		return "redirect:/management/solicitudes";
	}
	
	@GetMapping("/management/solicitudes/decline/{id}")
	public String rechazaSolicitud(@PathVariable ("id") int id , Model model) {
		
		Solicitud solicitud = servicio.findById(id);
		
		if (solicitud != null) {
			solicitud.setEstadoSolicitud(estados.findById(3));
			servicio.add(solicitud);
		}
		
		return "redirect:/management/solicitudes";
	}
}
