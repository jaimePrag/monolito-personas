package com.monolito.personas.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "personas")
@Data
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotBlank
    @Column(columnDefinition = "varchar(255)")
    private String nombres;

    @Column(columnDefinition = "varchar(255)")
    private String apellidos;

    @Column(name = "tipo_identificacion")
    private String tipoIdentificacion;

    @Column()
    private String identificacion;

    @NotNull
    @Min(value = 1)
    private int edad;

    @Column(name = "ciudad_nacimiento")
    private String ciudadNacimiento;

    @OneToMany(mappedBy = "persona")
    private List<Imagen> imagenes;
    
}
