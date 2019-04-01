package com.saahas.demo.api.vi.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.saahas.demo.api.v1.model.CustomerDTO;
import com.saahas.demo.domain.Customer;


public class CustomerMapperTest {

	private CustomerMapper mapper = CustomerMapper.INSTANCE;
	
	@Test
	public void testCustomerToCustomerDTO() {
		Customer cus = new Customer();
		cus.setCustomerId(1L);
		cus.setFirstName("Matt");
		cus.setLastName("Damon");
		
		CustomerDTO customerDTO = mapper.customerToCustomerDTO(cus);
		
		Assert.assertNotNull(customerDTO);
		Assert.assertEquals("Matt", customerDTO.getFirstName());
		Assert.assertEquals("Damon", customerDTO.getLastName());
	}

	@Test
	public void testCustomerDTOTOCustomer(){
		CustomerDTO dto = new CustomerDTO();
		dto.setCustomerId(1L);
		dto.setFirstName("Matt");
		dto.setLastName("Damon");
		
		Customer customer = mapper.customerDTOToCustomer(dto);
		
		Assert.assertNotNull(customer);
		Assert.assertEquals("Matt", customer.getFirstName());
		Assert.assertEquals("Damon", customer.getLastName());
	}
	
	
}
