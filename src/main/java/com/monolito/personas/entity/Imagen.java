package com.monolito.personas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotNull
    private String nombre;

    @Lob
    @NotNull
    private byte[] data;

    @ManyToOne()
    @JoinColumn(name = "persona_id")
    @JsonIgnore
    @NotNull
    private Persona persona;
}
