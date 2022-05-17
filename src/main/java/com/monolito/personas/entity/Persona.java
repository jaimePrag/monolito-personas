package com.monolito.personas.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "personas")
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "varchar(255)")
    private String nombres;

    @Column(columnDefinition = "varchar(255)")
    private String apellidos;

    private int edad;
}
