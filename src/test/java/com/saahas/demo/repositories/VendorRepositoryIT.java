package com.saahas.demo.repositories;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.saahas.demo.bootstrap.Bootstrap;
import com.saahas.demo.domain.Vendor;


@RunWith(SpringRunner.class)
@DataJpaTest
public class VendorRepositoryIT {
	
	@Autowired
	private VendorRepository vendorRepo;
	
	@Before
	public void setup() throws Exception {
		
		Bootstrap bootstrap = new Bootstrap(vendorRepo);
		bootstrap.loadVendors();
		
		System.out.println("Loaded vendors in in-memory DB");
	}
	
	@Test
	public void testFindByName() {
		Vendor vendor = vendorRepo.getByName("vendor1");
		
		Assert.assertNotNull(vendor);
		Assert.assertThat(vendor.getId(), Matchers.is(1L));
		
		Vendor vendor2 = vendorRepo.getByName("vendor2");
		
		Assert.assertNotNull(vendor2);
		Assert.assertThat(vendor2.getId(), Matchers.is(2L));
	}

}
