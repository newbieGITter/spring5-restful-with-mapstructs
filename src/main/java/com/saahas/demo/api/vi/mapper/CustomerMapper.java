package com.saahas.demo.api.vi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.saahas.demo.api.v1.model.CustomerDTO;
import com.saahas.demo.domain.Customer;

@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	CustomerDTO customerToCustomerDTO(Customer customer);
	
	Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
