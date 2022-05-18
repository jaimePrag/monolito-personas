package com.monolito.personas.service;

import java.util.List;
import java.util.Optional;

import com.monolito.personas.entity.Persona;

public interface IPersonaService {

  public List<Persona> getAll();

  public Optional<Persona> findById(long id);

  public Persona save(Persona persona);

  public boolean delete(long id);
}
