package com.monolito.personas.controller;

import com.monolito.personas.dto.Image;
import com.monolito.personas.dto.Person;
import com.monolito.personas.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    IPersonService personaService;

    @GetMapping()
    public List<Person> index() {
        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable long id) {
        Optional<Person> person;
        Map<String, Object> response = new HashMap<>();

        person = personaService.findById(id);
        if (!person.isPresent()) {
            response.put("mensaje", String.format("No se encontro la persona con el ID = %d", id));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> store(@Valid @RequestBody Person persona, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        Person nuevaPersona = personaService.save(persona);
        response.put("mensaje", String.format("Se ha creado la persona %s con éxito", nuevaPersona.getNames()));
        response.put("persona", nuevaPersona);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody Person persona, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Person> oldPersona = personaService.findById(id);
        if (!oldPersona.isPresent()) {
            response.put("mensaje", String.format("No se encontro la persona con el ID = %d", id));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        persona.setId(id);
        personaService.save(persona);
        response.put("mensaje", String.format("Se ha actualizado la persona %s con éxito", persona.getNames()));
        response.put("persona", persona);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable long id) {
        Map<String, Object> response = new HashMap<>();
        if (!personaService.delete(id)) {
            response.put("mensaje", "No se encontro la persona con id " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "la persona ha sido eliminada con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
