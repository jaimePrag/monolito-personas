package com.monolito.personas.controller;

import com.monolito.personas.dto.Person;
import com.monolito.personas.exception.FieldErrorException;
import com.monolito.personas.exception.ImageRequiredException;
import com.monolito.personas.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    IPersonService personService;

    @GetMapping()
    public List<Person> index() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> show(@PathVariable long id) {
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> store(@Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }
        person.setId(null);
        Person nuevaPersona = personService.save(person);
        Map<String, Object> response = Map.of(
                "message", String.format("Se ha creado la persona %s con éxito", person.getNames()),
                "persona", nuevaPersona);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Person person, BindingResult result) {
        if (result.hasErrors()) {
            throw new FieldErrorException(result);
        }
        personService.findById(id); // throw an exception if it doesn't exist
        person.setId(id);
        Person personUpdated = personService.save(person);
        Map<String, Object> response = Map.of(
                "message", String.format("Se ha actualizado la persona %s con éxito", person.getNames()),
                "persona", personUpdated);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable Long id) {
        personService.delete(id); // throw an exception if it doesn't exist
        Map<String, Object> response = Map.of("message", "la persona ha sido eliminada con éxito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/{id}/images/upload")
    public ResponseEntity<?> upload(
        @RequestParam("archivo") Optional<MultipartFile> archivo,
        @PathVariable("id") Long personId) {
      Person person = personService.findById(personId);
      MultipartFile file = archivo.orElseThrow(() -> new ImageRequiredException());
      personService.saveImage(file, person);
      Map<String, Object> response = Map.of("message", "La imagen se ha subido correctamente");
      return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
