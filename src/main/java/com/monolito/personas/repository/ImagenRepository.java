package com.monolito.personas.repository;

import com.monolito.personas.entity.Imagen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends CrudRepository<Imagen, Long> {

}
