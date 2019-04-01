package com.saahas.demo.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.saahas.demo.api.v1.model.CustomerDTO;
import com.saahas.demo.services.CustomerService;

public class CustomerControllerTest extends AbstractRestControllerTest {

	@Mock
	private CustomerService customerService;
	
	@InjectMocks
	private CustomerController customerController;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}
	
	@Test
	public void testListCustomers() throws Exception {
		CustomerDTO cus1 = new CustomerDTO();
		cus1.setCustomerId(1L);
		cus1.setFirstName("Ajay");
		cus1.setLastName("Jadeja");
		
		CustomerDTO cus2 = new CustomerDTO();
		cus2.setCustomerId(2L);
		cus2.setFirstName("Ravindra");
		cus2.setLastName("Jadeja");
		
		List<CustomerDTO> customers = new ArrayList<>();
		customers.add(cus1);
		customers.add(cus2);
		
		Mockito.when(customerService.getAllCustomers()).thenReturn(customers);
		
		mockMvc.perform(get("/api/v1/customers/")
						.contentType(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$", hasSize(2)))
						.andExpect(jsonPath("$[0].customerId", is(1)))
				        .andExpect(jsonPath("$[0].firstName", is("Ajay")))
				        .andExpect(jsonPath("$[0].lastName", is("Jadeja")))
				        .andExpect(jsonPath("$[1].customerId", is(2)))
				        .andExpect(jsonPath("$[1].firstName", is("Ravindra")))
				        .andExpect(jsonPath("$[1].lastName", is("Jadeja")));
		
	}
	
	@Test
	public void testGetByCustomerFirstName() throws Exception {
		CustomerDTO cus1 = new CustomerDTO();
		cus1.setCustomerId(1L);
		cus1.setFirstName("Ajay");
		cus1.setLastName("Jadeja");
		
		CustomerDTO cus2 = new CustomerDTO();
		cus2.setCustomerId(2L);
		cus2.setFirstName("Ravindra");
		cus2.setLastName("Jadeja");
		
		Mockito.when(customerService.getByFirstName("Ajay")).thenReturn(cus1);
		
		mockMvc.perform(get("/api/v1/customers/Ajay")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.firstName", is("Ajay")))
					.andExpect(jsonPath("$.lastName", is("Jadeja")));
	}
	
	@Test
	public void testSaveCustomer() throws Exception {
		CustomerDTO cus1 = new CustomerDTO();
		cus1.setFirstName("Virat");
		cus1.setLastName("Kohli");
		
		CustomerDTO returnedDTO = new CustomerDTO();
		returnedDTO.setCustomerUrl("/api/v1/customers/1");
		returnedDTO.setFirstName("Virat");
		returnedDTO.setLastName("Kohli");
		
		Mockito.when(customerService.createNewCustomer(cus1)).thenReturn(returnedDTO);
		
		mockMvc.perform(post("/api/v1/customers/")
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(asJsonString(cus1)))
					   .andExpect(status().isCreated())
					   .andExpect(jsonPath("$.lastName", is("Kohli")))
					   .andExpect(jsonPath("$.customerUrl", is("/api/v1/customers/1")));
	}
	
	@Test
	public void testUpdateCustomer() throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Jackie");
		customerDTO.setLastName("Chan");
		
		CustomerDTO returnedDTO = new CustomerDTO();
		returnedDTO.setFirstName(customerDTO.getFirstName());
		returnedDTO.setLastName(customerDTO.getLastName());
		returnedDTO.setCustomerUrl("/api/v1/customers/1");
		
		Mockito.when(customerService.saveCustomerByDTO(Mockito.anyLong(), Mockito.any(CustomerDTO.class))).thenReturn(returnedDTO);
		
		mockMvc.perform(put("/api/v1/customers/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(asJsonString(customerDTO)))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.customerUrl", is("/api/v1/customers/1")))
						.andExpect(jsonPath("$.lastName", is("Chan")));
						
	}
	
	@Test
	public void testPatchCustomer() throws Exception {
		CustomerDTO custDTO = new CustomerDTO();
		custDTO.setFirstName("Eoin");
		
		CustomerDTO returnedDTO = new CustomerDTO();
		returnedDTO.setFirstName(custDTO.getFirstName());
		returnedDTO.setLastName("Morgan");
		returnedDTO.setCustomerUrl("/api/v1/customers/1");
		
		Mockito.when(customerService.patchCustomer(Mockito.anyLong(), Mockito.any(CustomerDTO.class))).thenReturn(returnedDTO);
		
		mockMvc.perform(patch("/api/v1/customers/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content((asJsonString(custDTO))))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.firstName", is("Eoin")))
						.andExpect(jsonPath("$.lastName", is("Morgan")));
						
	}
	
	
}
