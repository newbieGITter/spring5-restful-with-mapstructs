package com.saahas.demo.services;

import java.util.List;

import com.saahas.demo.api.v1.model.CustomerDTO;

public interface CustomerService {
	
	public List<CustomerDTO> getAllCustomers();
	
	public CustomerDTO getByFirstName(String firstName);
	
	public CustomerDTO getByLastName(String lastName);
	
	CustomerDTO createNewCustomer(CustomerDTO customerDTO);
	
    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

	public CustomerDTO patchCustomer(Long id, CustomerDTO cusDTO);
	
	void deleteCustomerById(Long id);

}
