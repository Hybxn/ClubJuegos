package com.clubjuegos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	protected String recursos[] = new String[] {"/css/**", "/imgs/**", "/js/**"};
	protected String partePublica[] = new String[] {"/", "/juegos", "/centros","/registro", "/registro/submit","/login"};
	protected String coordinador[] = new String[] {"/management/**"};
	protected String cooryresp[] = new String[] {"/management/juegos/**","/management/solicitudes/**", "/management/top/juegos"};
	protected String responsable[] = new String[] {"/management/incidencias/**"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(recursos).permitAll()
				.antMatchers(partePublica).permitAll()
				.antMatchers(cooryresp).hasAnyAuthority("Coordinador","Responsable de Mantenimiento")
				.antMatchers(responsable).hasAnyAuthority("Responsable de Mantenimiento")
				.antMatchers(coordinador).hasAnyAuthority("Coordinador")
				.antMatchers("/jugador/**").hasAnyAuthority("Jugador")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/panel")
				.failureUrl("/login?error=true")
				.usernameParameter("username")
				.passwordParameter("password")
				.and()
			.rememberMe()
				.tokenRepository(ptr())
				.userDetailsService(userDetailService)
				.and()
			.logout()
				.permitAll()
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/login?logout");
				
	}	
	
	BCryptPasswordEncoder bCryptPasswordEncoder; 

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		return bCryptPasswordEncoder;
	}
	
	@Autowired
	UserDatailsServiceImpl userDetailService;

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Autowired
	DataSource datasource;
	
	@Bean
	public PersistentTokenRepository ptr() {
		
		JdbcTokenRepositoryImpl jtri = new JdbcTokenRepositoryImpl();
		jtri.setDataSource(datasource);
		
		return jtri;		
	}
}
