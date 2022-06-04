package com.dev.api.springrest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dto.EmployeeDto;
import com.dev.api.springrest.exception.EmployeeException;
import com.dev.api.springrest.model.Employee;
import com.dev.api.springrest.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setCpf(employee.getCpf());

        return employeeDto;
    }

    public Employee toModel(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setCpf(employeeDto.getCpf().replace(".", "").replace("-", ""));

        return employee;
    }

    public String saveEmployee(EmployeeDto employeeDto) {
        Employee employee = toModel(employeeDto);
        employeeRepository.save(employee);
        return "Employee " + employee.getId() + " successfully saved!";

    }
    
    public EmployeeDto findOneEmployee(Long id) throws EmployeeException {
    	return employeeRepository.findById(id)
    			.map(employee -> toDto(employee))
    			.orElseThrow(() -> new EmployeeException("Employee " + id + " not found. Please, try again."));
    }

    public String updateEmployee(Long id, EmployeeDto employeeDto) throws EmployeeException {      
    	Employee dataEmployee = this.employeeRepository.findById(id).orElseThrow(() -> 
    	new EmployeeException("Category " + id + " was not updated. Please, try again."));
    	
    	dataEmployee.setName(employeeDto.getName());
    	dataEmployee.setCpf(employeeDto.getCpf());
        employeeRepository.save(dataEmployee);
        return "Employee " + id + " successfully updated!";
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDto> listAll() {
        return employeeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

}