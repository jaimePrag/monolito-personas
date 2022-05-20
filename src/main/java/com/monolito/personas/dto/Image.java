package com.monolito.personas.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Image {

  private Long id;

  @NotBlank
  private String imageName;

  @NotBlank
  private byte[] data;
}
