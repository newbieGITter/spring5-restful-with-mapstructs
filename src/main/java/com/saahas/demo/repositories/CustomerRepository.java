package com.saahas.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saahas.demo.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	public Customer getByFirstName(String firstName);

	public Customer getByLastName(String lastName);
	
	public Customer getByCustomerId(Long id);
	 
}
