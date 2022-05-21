package com.monolito.personas.entity;

import lombok.Data;



import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;


@Entity
@Data
@Table(name = "personas", indexes = {
        @Index(columnList = "tipo_identificacion, identificacion"),
        @Index(columnList = "edad") })
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "varchar(255)")
    private String nombres;

    @Column(columnDefinition = "varchar(255)")
    private String apellidos;

    @NotNull
    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;

    @NotNull
    @Column()
    private String identificacion;

    @NotNull
    private int edad;

    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimiento;

    @Null
    @Column(name = "imagen_id")
    private String imagenId;
}