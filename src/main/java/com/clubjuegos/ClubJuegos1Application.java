package com.clubjuegos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClubJuegos1Application {

	public static void main(String[] args) {
		SpringApplication.run(ClubJuegos1Application.class, args);
	}

//	@Bean
//	CommandLineRunner initData(JuegoRepository repository, CentroRepository centros, IdiomaRepository idiomas,
//			RolRepository roles) {
//		return (args) -> {
//
//			List<Centro> todoCentro = centros.findAll();
//			for (Centro centro : todoCentro) {
//
//				if (centro.getPais().equalsIgnoreCase("España")) {
//					repository.save(new Juego("Monopoly","",2,8,(int)(Math.random()*9)+1, centro, idiomas.findById(1).orElse(null)));
//					repository.save(new Juego("Trivial Pursuit","",2,6,(int)(Math.random()*9)+1, centro, idiomas.findById(1).orElse(null)));
//					repository.save(new Juego("Dixit","",3,6,(int)(Math.random()*9)+1, centro, idiomas.findById(1).orElse(null)));
//					repository.save(new Juego("Cluedo","",3,6,(int)(Math.random()*9)+1, centro, idiomas.findById(1).orElse(null)));
//					repository.save(new Juego("Risk","",2,6,(int)(Math.random()*9)+1, centro, idiomas.findById(1).orElse(null)));
//				} else {
//					repository.save(new Juego("Monopoly","",2,8,(int)(Math.random()*9)+1, centro, idiomas.findById(2).orElse(null)));
//					repository.save(new Juego("Trivial Pursuit","",2,6,(int)(Math.random()*9)+1, centro, idiomas.findById(2).orElse(null)));
//					repository.save(new Juego("Dixit","",3,6,(int)(Math.random()*9)+1, centro, idiomas.findById(2).orElse(null)));
//					repository.save(new Juego("Cluedo","",3,6,(int)(Math.random()*9)+1, centro, idiomas.findById(2).orElse(null)));
//					repository.save(new Juego("Risk","",2,6,(int)(Math.random()*9)+1, centro, idiomas.findById(2).orElse(null)));
//					
//				}
//			}
//
//			repository.findAll().forEach(System.out::println);
//		};
//	}

}
