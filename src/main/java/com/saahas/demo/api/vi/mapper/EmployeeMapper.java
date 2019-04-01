package com.saahas.demo.api.vi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.saahas.demo.api.v1.model.EmployeeDTO;
import com.saahas.demo.domain.Employee;

@Mapper
public interface EmployeeMapper {
	
	EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
	
	EmployeeDTO employeeToEmployeeDTO(Employee employee);
	
	Employee employeeDTOToEmployee(EmployeeDTO employeeDTO);

}
