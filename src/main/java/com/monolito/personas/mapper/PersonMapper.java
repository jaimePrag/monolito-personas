package com.monolito.personas.mapper;

import java.util.List;

import com.monolito.personas.dto.Image;
import com.monolito.personas.dto.Person;
import com.monolito.personas.entity.Persona;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = { ImageMapper.class })
public interface PersonMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombres", target = "names"),
            @Mapping(source = "apellidos", target = "surnames"),
            @Mapping(source = "tipoIdentificacion", target = "identificationType"),
            @Mapping(source = "identificacion", target = "identification"),
            @Mapping(source = "edad", target = "age"),
            @Mapping(source = "ciudadNacimiento", target = "birthCity"),
            @Mapping(source = "imagen", target = "image")
    })
    Person toPerson(Persona persona);

    List<Person> toPersons(List<Persona> personas);

    @InheritInverseConfiguration
    Persona toPersona(Person person);
}
