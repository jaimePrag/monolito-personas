package com.monolito.personas.mapper;

import java.util.List;

import com.monolito.personas.dto.Image;
import com.monolito.personas.entity.Imagen;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ImageMapper {
  @Mappings({
      @Mapping(source = "id", target = "id"),
      @Mapping(source = "imagenNombre", target = "imageName"),
      @Mapping(source = "data", target = "data"),

  })
  Image toImage(Imagen imagen);

  List<Image> toImages(List<Imagen> imagenes);

  @InheritInverseConfiguration
  Imagen toImagen(Image image);
}
