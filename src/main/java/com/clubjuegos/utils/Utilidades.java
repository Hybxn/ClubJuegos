package com.clubjuegos.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.clubjuegos.models.Usuario;
import com.clubjuegos.services.UsuarioService;

public class Utilidades {

	public static Usuario dameConectado(UsuarioService usuarios) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		Usuario conectado = usuarios.findByEmail(userDetails.getUsername()).orElse(null);

		return conectado;
	}
	
	
	public static Map<String, Double> ordenarPorPuntuacion(Map<String, Double> top) {

		List<String> juegos = new ArrayList<>();
		List<Double> puntuaciones = new ArrayList<>();

		for (Map.Entry<String, Double> entry : top.entrySet()) {
			juegos.add(entry.getKey());
			puntuaciones.add(entry.getValue());
		}
		boolean cambio = false;
		do {
			cambio = false;
			String sAux;
			Double fAux;

			for (int i = 1; i < puntuaciones.size(); i++) {
				if (puntuaciones.get(i).compareTo(puntuaciones.get(i - 1)) > 0) {

					cambio = true;

					sAux = juegos.get(i);
					fAux = puntuaciones.get(i);

					juegos.set(i, juegos.get(i - 1));
					puntuaciones.set(i, puntuaciones.get(i - 1));

					juegos.set(i - 1, sAux);
					puntuaciones.set(i - 1, fAux);
				}

			}

		} while (cambio);

		LinkedHashMap<String, Double> topJuegos = new LinkedHashMap<String, Double>();

		for (int i = 0; i < puntuaciones.size(); i++) {

			topJuegos.put(juegos.get(i), puntuaciones.get(i));
		}
		return topJuegos;
	}
}
