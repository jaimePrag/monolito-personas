package com.monolito.personas.service;

import com.monolito.personas.entity.Imagen;
import com.monolito.personas.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements IImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    @Transactional(readOnly = true)
    public List<Imagen> getAll() {
        return (List<Imagen>) imagenRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Imagen> findById(long id) {
        return imagenRepository.findById(id);
    }

    @Transactional
    public Imagen save(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    @Transactional
    public boolean delete(long id) {
        return findById(id).map((imagen) -> {
            imagenRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
