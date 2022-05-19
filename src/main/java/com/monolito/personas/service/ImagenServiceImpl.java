package com.monolito.personas.service;

import com.monolito.personas.dto.Image;
import com.monolito.personas.entity.Imagen;
import com.monolito.personas.mapper.ImageMapper;
import com.monolito.personas.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<Image> findById(long id) {
        return imagenRepository.findById(id).map(imagen -> mapper.toImage(imagen));
    }

    @Transactional
    public Image save(Image image) {
        Imagen imagen = mapper.toImagen(image);
        return mapper.toImage(imagenRepository.save(imagen));
    }

    @Transactional
    public boolean delete(long id) {
        return findById(id).map((imagen) -> {
            imagenRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
