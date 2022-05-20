package com.monolito.personas.service;

import java.util.List;

import com.monolito.personas.dto.Image;
import com.monolito.personas.dto.Person;

import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

  public List<Image> getAll();

  public Image findById(Long id);

  public Image save(MultipartFile file, Person person);

  public void delete(Long id);
}
