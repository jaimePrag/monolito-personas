package com.monolito.personas.entity;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "imagenes")
public class Imagen {
    @Id
    private String id;

    private String nombre;

    private Binary data;
}
