package com.monolito.personas.controller;

import com.monolito.personas.entity.Imagen;
import com.monolito.personas.entity.Persona;
import com.monolito.personas.service.IImagenService;
import com.monolito.personas.service.IPersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/imagenes")
@RestController
public class ImagenController {
    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IImagenService imagenService;

    @GetMapping()
    public List<Imagen> index() {
        List<Imagen> imgenes = imagenService.getAll();
        return imgenes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Imagen> imagen;
        Map<String, Object> response = new HashMap<>();

        imagen = imagenService.findById(id);
        if (!imagen.isPresent()) {
            response.put("mensaje", String.format("No se encontro la imagen con el ID = %d", id));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Imagen>(imagen.get(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> store(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {

        Map<String, Object> response = new HashMap<>();
        Optional<Persona> personaOpt = personaService.findById(id);

        if (!personaOpt.isPresent()) {
            response.put("mensaje", String.format("No se encontro la persona con el ID = %d", id));
            return new ResponseEntity<Map<String, Object>>(response,
                    HttpStatus.NOT_FOUND);
        }
        Persona persona = personaOpt.get();
        if (!archivo.isEmpty()) {
            String nombreArchivo = UUID.randomUUID() + "_" +
                    archivo.getOriginalFilename().replace(" ", "");
            Imagen imagen = new Imagen();
            imagen.setNombre(nombreArchivo);
            try {
                imagen.setData(archivo.getBytes());
            } catch (IOException e) {
                // TODO: contolar excepcion con controlAvisor -- Handle File Upload Exception
                // https://www.bezkoder.com/spring-boot-upload-file-database/
                e.printStackTrace();
            }

            imagen.setPersona(persona);
            imagenService.save(imagen);
            response.put("imagen", imagen);
            response.put("mensaje", "se ha subido la imagen correctamente");
        }

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    // @PutMapping("/{id}")
    // public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody
    // Imagen persona, BindingResult result) {
    // return null;
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        if (!imagenService.delete(id)) {
            response.put("mensaje", "No se encontro la imagen con id " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "la imagen ha sido eliminada con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
