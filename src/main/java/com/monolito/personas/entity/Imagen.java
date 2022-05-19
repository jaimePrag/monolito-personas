package com.monolito.personas.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "imagenes")
@Data
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "varchar(255)")
    private String imagenNombre;

    @Lob
    @NotNull
    private byte[] data;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "persona_id")
    // @NotNull
    // private Persona persona;
}
