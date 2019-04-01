package com.saahas.demo.api.vi.mapper;

import org.junit.Assert;
import org.junit.Test;

import com.saahas.demo.api.v1.model.EmployeeDTO;
import com.saahas.demo.domain.Employee;

public class EmployeeMapperTest {

	private EmployeeMapper mapper = EmployeeMapper.INSTANCE;

	@Test
	public void testEmployeeToEmployeeDTO_forNullEmployee() {
		//execution
		EmployeeDTO employeeDTO = mapper.employeeToEmployeeDTO(null);
		
		//Verification
		Assert.assertNull(employeeDTO);
	}
	
	@Test
	public void testEmployeeToEmployeeDTO() {
		//setup
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setFirstName("Roger");
		employee.setLastName("Federer");
		employee.setEmpCode("A001");
		employee.setSalary(100000);
		employee.setDesignation("Tennis legend");
		
		// execution
		EmployeeDTO employeeDTO = mapper.employeeToEmployeeDTO(employee);
		
		// verification
		Assert.assertNotNull(employeeDTO);
		Assert.assertEquals("Federer", employeeDTO.getLastName());
		Assert.assertEquals("Tennis legend", employeeDTO.getDesignation());
	}
	
	@Test
	public void testEmployeeDTOToEmployee_forNullEmployeeDTO() {
		// Execution
		Employee employee = mapper.employeeDTOToEmployee(null);
		
		// Verification
		Assert.assertNull(employee);

	}

}
