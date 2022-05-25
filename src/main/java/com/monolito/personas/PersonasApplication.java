package com.monolito.personas;

//import com.monolito.personas.repository.ImagenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PersonasApplication implements CommandLineRunner {
    //@Autowired
    //private ImagenRepository idr;

    public static void main(String[] args) {
        SpringApplication.run(PersonasApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //idr.findAll().forEach(img -> System.out.println(img));
        //idr.findAll();
    }
}
