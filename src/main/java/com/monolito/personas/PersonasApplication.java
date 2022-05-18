package com.monolito.personas;

// import com.monolito.personas.service.PersonaServiceImpl;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonasApplication implements CommandLineRunner {
	// @Autowired
	// private PersonaServiceImpl personaService;

	public static void main(String[] args) {
		SpringApplication.run(PersonasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// personaService.getAll().forEach(persona -> System.out.println(persona));
	}
}
