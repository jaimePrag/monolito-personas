package com.monolito.personas.controller;

import com.monolito.personas.dto.Image;
import com.monolito.personas.dto.Person;
import com.monolito.personas.exception.ImageRequiredException;
import com.monolito.personas.service.IImageService;
import com.monolito.personas.service.IPersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/images")
@RestController
public class ImageController {
  @Autowired
  private IPersonService personService;

  @Autowired
  private IImageService imagenService;

  @GetMapping()
  public List<Image> index() {
    return imagenService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> show(@PathVariable Long id) {
    Image image = imagenService.findById(id);
    return new ResponseEntity<Image>(image, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<?> upload(
      @RequestParam("archivo") Optional<MultipartFile> archivo,
      @RequestParam("person") Long personId) {
    Person person = personService.findById(personId);
    MultipartFile file = archivo.orElseThrow(() -> new ImageRequiredException());
    Image image = imagenService.save(file, person);
    Map<String, Object> response = Map.of(
        "message", "La imagen se ha subido correctamente",
        "imagen", image);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> destroy(@PathVariable Long id) {
    imagenService.delete(id);
    Map<String, Object> response = Map.of("message", "la imagen ha sido eliminada");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
