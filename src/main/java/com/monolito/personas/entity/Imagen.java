package com.monolito.personas.entity;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

import javax.persistence.Id;


@Data
@Document(collection = "imagenes")
public class Imagen {
    @Id
    private String id;

    private String nombre;

    private Binary data;
}
