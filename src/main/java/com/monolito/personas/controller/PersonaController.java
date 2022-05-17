package com.monolito.personas.controller;

import com.monolito.personas.entity.Persona;
import com.monolito.personas.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping()
    public List<Persona> index() {
        return personaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable int id) {
        Optional<Persona> persona;
        Map<String, Object> response = new HashMap<>();

        persona = personaService.findById(id);
        if (!persona.isPresent()) {
            response.put("mensaje", String.format("No se encontro la persona con el ID = %d", id));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Persona>(persona.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> store(@RequestBody Persona persona, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        Persona nuevaPersona = personaService.save(persona);
        response.put("mensaje", String.format("Se ha creado la persona %s con éxito", nuevaPersona.getNombres()));
        response.put("persona", nuevaPersona);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Persona persona, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Persona> oldPersona = personaService.findById(id);
        if (!oldPersona.isPresent()) {
            response.put("mensaje", String.format("No se encontro la persona con el ID = %d", id));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        persona.setId(id);
        personaService.save(persona);
        response.put("mensaje", String.format("Se ha actualizado la persona %s con éxito", persona.getNombres()));
        response.put("persona", persona);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        if (!personaService.delete(id)) {
            response.put("mensaje", "No se encontro la persona con id " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "la persona ha sido eliminada con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
