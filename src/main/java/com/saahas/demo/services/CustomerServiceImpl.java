package com.saahas.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.saahas.demo.api.v1.model.CustomerDTO;
import com.saahas.demo.api.vi.mapper.CustomerMapper;
import com.saahas.demo.domain.Customer;
import com.saahas.demo.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepo;
	
	private CustomerMapper mapper = CustomerMapper.INSTANCE;
	
	public CustomerServiceImpl(CustomerMapper mapper, CustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
		this.mapper = mapper;
	}

	@Override
	public CustomerDTO getByFirstName(String firstName) {
		return mapper.customerToCustomerDTO(customerRepo.getByFirstName(firstName));
	}

	@Override
	public CustomerDTO getByLastName(String lastName) {
		return mapper.customerToCustomerDTO(customerRepo.getByLastName(lastName));
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepo.findAll().stream().map(c -> mapper.customerToCustomerDTO(c)).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
		return saveAndReturnDTO(mapper.customerDTOToCustomer(customerDTO));
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
		Customer customer = mapper.customerDTOToCustomer(customerDTO);
        customer.setCustomerId(id);

        return saveAndReturnDTO(customer);
	}
	
	private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepo.save(customer);

        CustomerDTO returnDto = mapper.customerToCustomerDTO(savedCustomer);

        returnDto.setCustomerUrl("/api/v1/customer/" + savedCustomer.getCustomerId());

        return returnDto;
    }

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO cusDTO) {
		return customerRepo.findById(id).map(customer -> {
			if(cusDTO.getFirstName() != null) {
				customer.setFirstName(cusDTO.getFirstName());
			}
			
			if(cusDTO.getLastName() != null){
				customer.setLastName(cusDTO.getLastName());
			}
			return mapper.customerToCustomerDTO(customerRepo.save(customer));
		}).orElseThrow(RuntimeException::new);		
	}

	@Override
	public void deleteCustomerById(Long id) {
		Customer customer = customerRepo.getByCustomerId(id);
		customerRepo.delete(customer);
	}
	
}
