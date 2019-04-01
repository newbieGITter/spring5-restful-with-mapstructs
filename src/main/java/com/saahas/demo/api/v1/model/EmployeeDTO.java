package com.saahas.demo.api.v1.model;

import lombok.Data;

@Data
public class EmployeeDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String empCode;
	private int salary;
	private String designation;
}
