package com.monolito.personas.service;

import com.monolito.personas.dto.Person;
import com.monolito.personas.entity.Imagen;
import com.monolito.personas.entity.Persona;
import com.monolito.personas.exception.ImageRequiredException;
import com.monolito.personas.exception.PersonNotFoundException;
import com.monolito.personas.exception.ReadImageFileException;
import com.monolito.personas.mapper.PersonMapper;
import com.monolito.personas.repository.ImagenRepository;
import com.monolito.personas.repository.PersonaRepository;

import com.sun.tools.jconsole.JConsoleContext;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonaServiceImpl implements IPersonService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private PersonMapper mapper;

    @Transactional(readOnly = true)
    public List<Person> getAll() {
        List<Person> persons = mapper.toPersons((List<Persona>) personaRepository.findAll());
        for (Person person : persons) {
            person.setImage(getImageFileFromImageId(person.getImageId()));
        }
        return persons;
    }

    @Transactional(readOnly = true)
    public Person findById(Long id) {
        Persona persona = personaRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
        Person person = mapper.toPerson(persona);
        byte[] imagen = getImageFileFromImageId(persona.getImagenId());
        person.setImage(imagen);
        return person;
    }

    @Transactional
    public Person save(Person person) {
        Persona persona = mapper.toPersona(person);
        if (person.getId() != null) {
            persona.setImagenId(findById(person.getId()).getImageId());
        }
        person =  mapper.toPerson(personaRepository.save(persona));
        person.setImage(getImageFileFromImageId(person.getImageId()));
        return person;
    }

    @Transactional
    public void delete(Long id) {
        Person person = findById(id); // throw an exception if it doesn't exist
        personaRepository.deleteById(id);
        imagenRepository.deleteById(person.getImageId());
    }

    @Transactional
    public void saveImage(MultipartFile archivo, Person person) {
        if (archivo.isEmpty()) {
            throw new ImageRequiredException();
        }
        String nombreArchivo = UUID.randomUUID() + "_" + archivo.getOriginalFilename().replace(" ", "");
        Imagen imagen = new Imagen();
        imagen.setId(person.getImageId());
        try {
            imagen.setData(new Binary(BsonBinarySubType.BINARY, archivo.getBytes()));
        } catch (IOException e) {
            throw new ReadImageFileException(e);
        }
        imagen.setNombre(nombreArchivo);
        imagen = imagenRepository.save(imagen);
        personaRepository.associateImagetoPerson(imagen.getId(), person.getId());
    }

    public byte[] getImageFileFromImageId(String imagenId) {
        return Optional.ofNullable(imagenId)
                .flatMap(imgId -> imagenRepository.findById(imgId))
                .map(img -> img.getData().getData())
                .orElse(null);
    }

    public void setMapper(PersonMapper mapper) {
        this.mapper = mapper;
    }

}
