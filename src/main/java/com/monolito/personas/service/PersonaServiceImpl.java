package com.monolito.personas.service;

import com.monolito.personas.dto.Person;
import com.monolito.personas.entity.Persona;
import com.monolito.personas.mapper.PersonMapper;
import com.monolito.personas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonMapper mapper;

    @Transactional(readOnly = true)
    public List<Person> getAll() {
        List<Persona> personas = (List<Persona>) personaRepository.findAll();
        return mapper.toPersons(personas);
    }

    @Transactional(readOnly = true)
    public Optional<Person> findById(long id) {
        return personaRepository.findById(id).map(persona -> mapper.toPerson(persona));
    }

    @Transactional
    public Person save(Person person) {
        Persona persona = mapper.toPersona(person);
        return mapper.toPerson(personaRepository.save(persona));
    }

    @Transactional
    public boolean delete(long id) {
        return findById(id).map((persona) -> {
            personaRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

}
