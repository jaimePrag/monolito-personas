package com.monolito.personas.service;

import java.util.List;

import com.monolito.personas.dto.Person;

public interface IPersonService {

  public List<Person> getAll();

  public Person findById(Long id);

  public Person save(Person persona);

  public void delete(Long id);
}
