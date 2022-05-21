package com.monolito.personas.repository;

import com.monolito.personas.entity.Imagen;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagenRepository extends MongoRepository<Imagen, String> {

}
