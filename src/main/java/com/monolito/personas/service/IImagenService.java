package com.monolito.personas.service;

import java.util.List;
import java.util.Optional;

import com.monolito.personas.entity.Imagen;

public interface IImagenService {

  public List<Imagen> getAll();

  public Optional<Imagen> findById(long id);

  public Imagen save(Imagen imagen);

  public boolean delete(long id);
}
