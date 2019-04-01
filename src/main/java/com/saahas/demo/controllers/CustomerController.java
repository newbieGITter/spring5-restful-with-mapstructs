package com.saahas.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.saahas.demo.api.v1.model.CustomerDTO;
import com.saahas.demo.services.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/api/v1/customers/")
	public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
		return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
	}
	
	@GetMapping("/api/v1/customers/{firstName}")
	public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstName) {
		return new ResponseEntity<>(customerService.getByFirstName(firstName), HttpStatus.OK);
	}
	
	@PostMapping("/api/v1/customers")	
	public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO custDTO){
		return new ResponseEntity<>(customerService.createNewCustomer(custDTO), HttpStatus.CREATED);
	}
	
	@PutMapping({"/api/v1/customers/{id}"})
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<CustomerDTO>(customerService.saveCustomerByDTO(id, customerDTO),
                HttpStatus.OK);
    }
	
	@PatchMapping("/api/v1/customers/{id}")
	public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO custDTO) {
		return new ResponseEntity<CustomerDTO>(customerService.patchCustomer(id, custDTO), HttpStatus.OK);
	}
	
	
}
