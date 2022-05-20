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
    private Long id;

    @NotNull
    @Column(columnDefinition = "varchar(255)")
    private String imagenNombre;

    @Lob
    @NotNull
    private byte[] data;

    @OneToOne(mappedBy = "imagen")
    private Persona persona;

    @PreRemove()
    public void setImageIdNull() {
        this.persona.setImagen(null);
    }
}
