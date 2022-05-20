package com.monolito.personas.exception;

import java.io.IOException;

public class ReadImageFileException extends RuntimeException {

  public ReadImageFileException(IOException ex) {
    super("Ocurrio un error al tratar de leer la imagen", ex);
  }

}
