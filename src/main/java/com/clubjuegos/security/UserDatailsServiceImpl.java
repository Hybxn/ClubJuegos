package com.clubjuegos.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clubjuegos.models.Rol;
import com.clubjuegos.models.Usuario;
import com.clubjuegos.repositories.UsuarioRepository;

@Service
public class UserDatailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarios;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuarioConectado = usuarios.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();	
		
		Rol autorizacion = usuarioConectado.getRolUsuario();

		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(autorizacion.getNombreRol());
		
		grantList.add(grantedAuthority);
		
		UserDetails user = (UserDetails) new User(usuarioConectado.getEmail(), usuarioConectado.getPassword(), grantList);
		
		return user;
	}

}
