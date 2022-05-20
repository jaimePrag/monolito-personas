package com.monolito.personas.repository;

import com.monolito.personas.entity.Imagen;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends CrudRepository<Imagen, Long> {

  @Modifying
  @Query(value = "update Persona p set imagen_id = ?1 where p.id = ?2")
  void associateImagetoPerson(Long imageId, Long personId);
}
