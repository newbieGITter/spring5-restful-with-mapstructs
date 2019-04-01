package com.saahas.demo.api.v1.model;

import lombok.Data;

@Data
public class CustomerDTO {

	private Long customerId;
	private String firstName;
	private String lastName;
	private String customerUrl;
}
