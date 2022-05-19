package com.monolito.personas.dto;

import lombok.Data;

@Data
public class Image {
  private long id;
  private String imageName;
  private byte[] data;
}
