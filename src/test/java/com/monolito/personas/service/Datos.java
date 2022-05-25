package com.monolito.personas.service;


import com.monolito.personas.entity.Imagen;
import com.monolito.personas.entity.Persona;
import org.bson.types.Binary;

import java.util.Arrays;
import java.util.List;

public class Datos {
    public static final List<Persona> PERSONAS = Arrays.asList(
            new Persona(1L, "felix", "perez", "CC", "112", 22, "CALI", "1"),
            new Persona(2L, "pepe", "ortiz", "TI", "113", 15, "NEIVA", null),
            new Persona(3L, "juan", "perea", "CC", "114", 28, "CALI", "3")
    );
    public static final Persona PERSONA = new Persona(1L, "felix", "perez", "CC", "112", 22, "CALI", "1");

    public static final List<Imagen> IMAGENES = Arrays.asList(
            new Imagen("1", "1.jpg", new Binary("1.jpg".getBytes())),
            new Imagen("3", "3.jpg", new Binary("2.jpg".getBytes()))
    );

    public static final Imagen IMAGEN =  new Imagen("3", "3.jpg", new Binary("2.jpg".getBytes()));

}
