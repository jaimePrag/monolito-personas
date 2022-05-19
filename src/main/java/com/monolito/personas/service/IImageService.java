package com.monolito.personas.service;

import java.util.List;
import java.util.Optional;

import com.monolito.personas.dto.Image;

public interface IImageService {

  public List<Image> getAll();

  public Optional<Image> findById(long id);

  public Image save(Image imagen);

  public boolean delete(long id);
}
