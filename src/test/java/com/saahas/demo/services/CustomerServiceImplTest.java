package com.saahas.demo.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.saahas.demo.api.v1.model.CustomerDTO;
import com.saahas.demo.api.vi.mapper.CustomerMapper;
import com.saahas.demo.domain.Customer;
import com.saahas.demo.repositories.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

	@Mock
	private CustomerRepository customerRepo;

	private CustomerService customerService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepo);
	}

	@Test
	public void testGetByCustomerFirstName() {
		Customer cus1 = new Customer();
		cus1.setCustomerId(1L);
		cus1.setFirstName("Mark");
		cus1.setLastName("Twain");

		Mockito.when(customerRepo.getByFirstName("Mark")).thenReturn(cus1);

		CustomerDTO customerDTO = customerService.getByFirstName("Mark");

		Assert.assertNotNull(customerDTO);
		Assert.assertEquals("Twain", customerDTO.getLastName());
	}

	@Test
	public void testGetByCustomerLastName() {
		Customer cus1 = new Customer();
		cus1.setCustomerId(1L);
		cus1.setFirstName("Mark");
		cus1.setLastName("Twain");

		Mockito.when(customerRepo.getByLastName("Twain")).thenReturn(cus1);

		CustomerDTO customerDTO = customerService.getByLastName("Twain");

		Assert.assertNotNull(customerDTO);
		Assert.assertEquals("Mark", customerDTO.getFirstName());
	}

	@Test
	public void testGetAllCustomers() {
		Customer cus1 = new Customer();
		cus1.setCustomerId(1L);
		cus1.setFirstName("Elon");
		cus1.setLastName("Musk");

		Customer cus2 = new Customer();
		cus2.setCustomerId(2L);
		cus2.setFirstName("Steve");
		cus2.setLastName("Jobs");

		List<Customer> customers = new ArrayList<>();
		customers.add(cus1);
		customers.add(cus2);

		Mockito.when(customerRepo.findAll()).thenReturn(customers);

		List<CustomerDTO> allCustomers = customerService.getAllCustomers();

		Assert.assertTrue(allCustomers.size() > 0);
		Assert.assertEquals("Elon", customers.get(0).getFirstName());
		Assert.assertEquals("Musk", customers.get(0).getLastName());
		Assert.assertEquals("Steve", customers.get(1).getFirstName());
		Assert.assertEquals("Jobs", customers.get(1).getLastName());

	}

	@Test
	public void testSaveCustomer() {
		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName("Ajay");
		savedCustomer.setLastName("Jadeja");
		savedCustomer.setCustomerId(1L);

		Mockito.when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(savedCustomer);

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Ajay");

		CustomerDTO savedCustomerDTO = customerService.createNewCustomer(customerDTO);

		Assert.assertNotNull(savedCustomerDTO);
		Assert.assertEquals("Jadeja", savedCustomerDTO.getLastName());
		Assert.assertEquals("/api/v1/customer/" + savedCustomer.getCustomerId(), savedCustomerDTO.getCustomerUrl());
	}

	@Test
	public void testUpdateCustomer() {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Jim");

		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setCustomerId(1l);

		Mockito.when(customerRepo.save(Mockito.any(Customer.class))).thenReturn(savedCustomer);

		// when
		CustomerDTO savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

		// then
		assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
		assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
	}
	
	@Test
	public void testDeleteCustomer() {
		Customer cust = new Customer();
		cust.setFirstName("Shane");
		cust.setLastName("Warne");
		cust.setCustomerId(111L);
		Mockito.when(customerRepo.getByCustomerId(1L)).thenReturn(cust);
		
		customerService.deleteCustomerById(1L);
		
		Mockito.verify(customerRepo, Mockito.times(1)).getByCustomerId(1L);
		Mockito.verify(customerRepo, Mockito.times(1)).delete(cust);
	}
}
