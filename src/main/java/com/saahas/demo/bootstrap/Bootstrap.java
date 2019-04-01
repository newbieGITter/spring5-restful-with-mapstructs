package com.saahas.demo.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.saahas.demo.domain.Category;
import com.saahas.demo.domain.Customer;
import com.saahas.demo.domain.Vendor;
import com.saahas.demo.repositories.CategoryRepository;
import com.saahas.demo.repositories.CustomerRepository;
import com.saahas.demo.repositories.VendorRepository;

@Component
public class Bootstrap implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	public Bootstrap() {
	}

	public Bootstrap(CategoryRepository categoryRepository) {
		super();
		this.categoryRepository = categoryRepository;
	}
	
	public Bootstrap(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
	}
	
	public Bootstrap(VendorRepository vendorRepository) {
		super();
		this.vendorRepository = vendorRepository;
	}
	
	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
			VendorRepository vendorRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		loadCategories();
		
		loadCustomers();
		
		loadVendors();
	}

	public void loadVendors() {
		Vendor vendor1 = new Vendor();
		vendor1.setName("vendor1");
		
		Vendor vendor2 = new Vendor();
		vendor2.setName("vendor2");
		
		Vendor vendor3 = new Vendor();
		vendor3.setName("vendor3");
		
		vendorRepository.save(vendor1);
		vendorRepository.save(vendor2);
		vendorRepository.save(vendor3);
	}

	public void loadCategories() {
		Category fruits = new Category();
		fruits.setName("fruits");
		
		Category dried = new Category();
		dried.setName("dried");
		
		Category fresh = new Category();
		fresh.setName("fresh");

		Category exotic = new Category();
		exotic.setName("exotic");
		
		Category nuts = new Category();
		nuts.setName("nuts");
		
		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);
		
		System.out.println("Saved categories, count: " + categoryRepository.count());
	}

	public void loadCustomers() {
		Customer cus1 = new Customer();
		cus1.setFirstName("Ajay");
		cus1.setLastName("Jadeja");
		
		Customer cus2 = new Customer();
		cus2.setFirstName("Ravindra");
		cus2.setLastName("Jadeja");
		
		Customer cus3 = new Customer();
		cus3.setFirstName("Hardik");
		cus3.setLastName("Pandya");
		
		customerRepository.save(cus1);
		customerRepository.save(cus2);
		customerRepository.save(cus3);
		
		System.out.println("Saved customers, count = " + customerRepository.count());
	}

}
