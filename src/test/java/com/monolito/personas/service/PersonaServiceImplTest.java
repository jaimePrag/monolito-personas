package com.monolito.personas.service;

import com.monolito.personas.dto.Person;
import com.monolito.personas.entity.Persona;
import com.monolito.personas.exception.PersonNotFoundException;
import com.monolito.personas.mapper.PersonMapper;
import com.monolito.personas.repository.ImagenRepository;
import com.monolito.personas.repository.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonaServiceImplTest {
    @MockBean
    PersonaRepository personaRepository;
    @MockBean
    ImagenRepository imagenRepository;
    @Autowired
    PersonMapper mapper;
    @Autowired
    PersonaServiceImpl personaService;

    @BeforeEach
    void setUp() {
        personaService.setMapper(mapper);
    }

    @Test
    @DisplayName("Test Mapper")
    void pruebaMapper() {
        Persona persona = Datos.PERSONA;
        var person = mapper.toPerson(persona);
        boolean isEquals =
                (persona.getId() == person.getId()) &&
                        (persona.getNombres() == person.getNames()) &&
                        (persona.getApellidos() == person.getSurnames()) &&
                        (persona.getTipoIdentificacion() == person.getIdentificationType()) &&
                        (persona.getIdentificacion() == person.getIdentification()) &&
                        (persona.getEdad() == person.getAge()) &&
                        (persona.getCiudadNacimiento() == person.getBirthCity()) &&
                        (persona.getImagenId() == person.getImageId());

        assertTrue(isEquals);
    }

    @Test
    @DisplayName("Test retorno de lista de personas")
    void getAll() {
        when(personaRepository.findAll()).thenReturn(Datos.PERSONAS);
        List<Person> all = personaService.getAll();
        verify(personaRepository).findAll();
        assertNotNull(all);
        assertEquals(3, all.size());
    }


    @Test
    @DisplayName("Test PersonNotFoundException")
    void findByIdException() {
        when(personaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
        assertThrows(PersonNotFoundException.class, () -> {
            personaService.findById(5L);
        });
    }

    @Test
    @DisplayName("Test busqueda de persona con imagen")
    void findByIdImage() {
        when(personaRepository.findById(anyLong())).thenReturn(Optional.of(Datos.PERSONA));
        when(imagenRepository.findById(anyString())).thenReturn(Optional.of(Datos.IMAGEN));
        Person person = personaService.findById(3L);
        verify(personaRepository).findById(anyLong());
        verify(imagenRepository).findById(anyString());
        assertNotNull(person);
        assertNotNull(person.getImageId());
        assertNotNull(person.getImage());
    }

    @Test
    void getImageFileFromImageIdNotNull() {
        when(imagenRepository.findById(anyString())).thenReturn(Optional.of(Datos.IMAGEN));
        byte[] img = personaService.getImageFileFromImageId(anyString());
        assertNotNull(img);
    }

    @Test
    void getImageFileFromImageIdNull() {
        when(imagenRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        byte[] img = personaService.getImageFileFromImageId(anyString());
        assertNull(img);
    }
}