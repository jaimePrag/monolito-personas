package com.monolito.personas.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
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
	@NotBlank
	private String age;

	private String birthCity;

	@JsonIgnore
	private String imageId;

	private byte[] image;
}
