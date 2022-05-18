package com.monolito.personas.service;

import com.monolito.personas.entity.Persona;
import com.monolito.personas.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements IPersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Transactional(readOnly = true)
    public List<Persona> getAll(){
        return (List<Persona>) personaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Persona> findById(long id){
        return personaRepository.findById(id);
    }

    @Transactional
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }

    @Transactional
    public boolean delete(long id) {
        return findById(id).map((persona) -> {
            personaRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

}
