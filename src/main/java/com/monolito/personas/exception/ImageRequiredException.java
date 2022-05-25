package com.monolito.personas.exception;

public class ImageRequiredException extends RuntimeException {

    public ImageRequiredException() {
        super("El archivo de imagen es requerido");
    }
}
