package com.monolito.personas.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person {

    private Long id;

    @NotBlank
    private String names;

    private String surnames;

    @NotBlank
    private String identificationType;

    @NotBlank
    private String identification;

    @Min(value = 1)
    @Max(value = 120)
    private int age;

    @NotBlank
    private String birthCity;

    @JsonIgnore
    private String imageId;

    private byte[] image;
}
