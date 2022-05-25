package com.monolito.personas.service;

import java.util.List;

import com.monolito.personas.dto.Person;

import org.springframework.web.multipart.MultipartFile;

public interface IPersonService {

    public List<Person> getAll();

    public Person findById(Long id);

    public Person save(Person persona);

    public void delete(Long id);

    public void saveImage(MultipartFile archivo, Person person);
}
