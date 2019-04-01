package com.saahas.demo.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import com.saahas.demo.api.v1.model.CustomerDTO;
import com.saahas.demo.api.vi.mapper.CustomerMapper;
import com.saahas.demo.bootstrap.Bootstrap;
import com.saahas.demo.domain.Customer;
import com.saahas.demo.repositories.CategoryRepository;
import com.saahas.demo.repositories.CustomerRepository;
import com.saahas.demo.repositories.VendorRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private VendorRepository vendorRepo;
	
	private CustomerService customerService;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setup() throws Exception {
		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepo);
		
		Bootstrap bootstrap = new Bootstrap(categoryRepo, customerRepo, vendorRepo);
		bootstrap.run();
		
		System.out.println("Loaded customers in in-memory DB");
	}
	
	@Test
	public void testPatchUpdateFirstName() {
		String updatedName = "updatedFirstName";
		Long id = getCustomerIdValue();
		
		Customer cus1 = customerRepo.getOne(id);
		String originalFirstName = cus1.getFirstName();
		String originalLastName = cus1.getLastName();
		
		CustomerDTO cusDTO = new CustomerDTO();
		cusDTO.setFirstName(updatedName);
		
		customerService.patchCustomer(id, cusDTO);
		
		Customer updatedCustomer = customerRepo.findById(id).get();
		
		assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
	}
	
	@Test
	public void testDeleteCustomerById() {
		Long custId = getCustomerIdValue();
		Customer cus1 = customerRepo.getOne(custId);
		Assert.assertNotNull(cus1);
		Assert.assertNotNull(cus1.getFirstName());

		thrown.expect(JpaObjectRetrievalFailureException.class);
		thrown.expectMessage("Unable to find com.saahas.demo.domain.Customer with id " + custId);
		
		customerService.deleteCustomerById(custId);
		
		Customer cusAfterDelete = customerRepo.getOne(custId);
		Assert.assertNull(cusAfterDelete);
		
	}
	
	private Long getCustomerIdValue() {
		List<Customer> customers = customerRepo.findAll();

        System.out.println("Customers Found: " + customers.size());

        //return first id
        return customers.get(0).getCustomerId();
	}
	
	
}
