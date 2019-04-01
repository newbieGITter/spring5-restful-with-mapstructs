package com.saahas.demo.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.saahas.demo.bootstrap.Bootstrap;
import com.saahas.demo.domain.Customer;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryIT {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private VendorRepository vendorRepo;
	
	@Before
	public void setup() throws Exception {
		
		Bootstrap bootstrap = new Bootstrap(categoryRepo, customerRepo, vendorRepo);
		bootstrap.run();
		
		System.out.println("Loaded vendors in in-memory DB");
	}
	
	@Test
	public void testGetByFirstName_forNonExistingFirstName() {
		Customer cust = customerRepo.getByFirstName("non-existing");
		
		Assert.assertNull(cust);
	}
	
	@Test
	public void testGetByFirstName_forExistingCustomerRecord() {
		Customer cust = customerRepo.getByFirstName("Ajay");
		
		Assert.assertNotNull(cust);
		Assert.assertEquals("Jadeja", cust.getLastName());

	}

}
