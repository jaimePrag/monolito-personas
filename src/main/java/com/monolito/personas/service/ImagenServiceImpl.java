package com.monolito.personas.service;

import com.monolito.personas.dto.Image;
import com.monolito.personas.dto.Person;
import com.monolito.personas.entity.Imagen;
import com.monolito.personas.exception.ImageNotFoundException;
import com.monolito.personas.exception.ImageRequiredException;
import com.monolito.personas.exception.ReadImageFileException;
import com.monolito.personas.mapper.ImageMapper;
import com.monolito.personas.repository.ImagenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ImagenServiceImpl implements IImageService {

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ImageMapper mapper;

    @Transactional(readOnly = true)
    public List<Image> getAll() {
        List<Imagen> imagenes = (List<Imagen>) imagenRepository.findAll();
        return mapper.toImages(imagenes);
    }

    @Transactional(readOnly = true)
    public Image findById(Long id) {
        Imagen persona = imagenRepository.findById(id).orElseThrow(() -> new ImageNotFoundException(id));
        return mapper.toImage(persona);
    }

    @Transactional
    public Image save(MultipartFile archivo, Person person) {
        if (archivo.isEmpty()) {
            throw new ImageRequiredException();
        }
        String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename().replace(" ", "");
        Imagen imagen = (person.getImage() != null)
                ? mapper.toImagen(person.getImage())
                : new Imagen();

        try {
            imagen.setData(archivo.getBytes());
        } catch (IOException e) {
            throw new ReadImageFileException(e);
        }

        imagen.setImagenNombre(nombreArchivo);
        imagen = imagenRepository.save(imagen);
        imagenRepository.associateImagetoPerson(imagen.getId(), person.getId());
        return mapper.toImage(imagen);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        imagenRepository.deleteById(id);
    }
}
