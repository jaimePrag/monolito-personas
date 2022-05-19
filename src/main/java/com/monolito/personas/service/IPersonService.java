package com.monolito.personas.service;

import java.util.List;
import java.util.Optional;

import com.monolito.personas.dto.Person;

public interface IPersonService {

  public List<Person> getAll();

  public Optional<Person> findById(long id);

  public Person save(Person persona);

  public boolean delete(long id);
}
