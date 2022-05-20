package com.monolito.personas.exception;

public class PersonNotFoundException extends RuntimeException {

  public PersonNotFoundException(Long id) {
    super(String.format("No se encontro la persona con el id %d", id));
  }
}
