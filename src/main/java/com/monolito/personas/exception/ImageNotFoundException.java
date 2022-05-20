package com.monolito.personas.exception;

public class ImageNotFoundException extends RuntimeException {

  public ImageNotFoundException(Long id) {
    super(String.format("No se encontro la imagen con el id %d", id));
  }
}
