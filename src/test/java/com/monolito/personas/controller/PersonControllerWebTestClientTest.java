package com.monolito.personas.controller;

import com.monolito.personas.dto.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerWebTestClientTest {

    @Autowired
    private WebTestClient clientHTTP;

    @Test
    void store() {
        Person person = new Person();
        person.setNames("pepito");
        person.setSurnames("juarez");
        person.setIdentificationType("CC");
        person.setIdentification("1114390142");
        person.setAge(100);
        person.setBirthCity("restrepo");

        clientHTTP.post().uri("http://localhost:8080/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(person)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.person.names").isNotEmpty()
                .jsonPath("$.person.surnames").isNotEmpty()
                .jsonPath("$.person.identificationType").isNotEmpty()
                .jsonPath("$.person.identification").isNotEmpty()
                .jsonPath("$.person.age").isNotEmpty()
                .jsonPath("$.person.birthCity").isNotEmpty()
                .jsonPath("$.person.image").isEqualTo(null)
                .jsonPath("$.message").value(is(String.format("Se ha creado la persona %s con éxito", person.getNames())));
    }

}