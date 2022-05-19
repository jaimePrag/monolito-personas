package com.monolito.personas.dto;

import lombok.Data;

@Data
public class Person {
	private long id;
	private String names;
	private String surnames;
	private String identificationType;
	private String identification;
	private String age;
	private String birthCity;
	private Image image;
}
