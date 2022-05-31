package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.EmployeeDto;
import com.dev.api.springrest.exceptions.EmployeeException;
import com.dev.api.springrest.models.Employee;
import com.dev.api.springrest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
	
    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDto employeeToDTO(Employee employee) {
        EmployeeDto employeeDTO = new EmployeeDto();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setCpf(employee.getCpf());
        return employeeDTO;
    }

    public Employee dtoToEmployee(EmployeeDto employeeDTO) {
        Employee employee = new Employee();

        employee.setName(employeeDTO.getName());
        employee.setCpf(employeeDTO.getCpf().replace(".", "").replace("-", ""));
        return employee;
    }

    public void saveEmployee(EmployeeDto employeeDTO) {
        Employee employee = dtoToEmployee(employeeDTO);
        employeeRepository.save(employee);
    }
    
    public EmployeeDto findOneEmployee(Long id) throws EmployeeException {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee dataEmployee;
        EmployeeDto employeeDTO = new EmployeeDto();
        
        if (employee.isPresent()) {
           dataEmployee = employee.get();
           employeeDTO = employeeToDTO(employee.get());
           return employeeDTO;
    }
    throw new EmployeeException("Employee " + dataEmployee.getId() + " not found. Please, try again.");
}
    
    public String updateEmployee(Long id, EmployeeDto employeeDTO) throws EmployeeException {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee dataEmployee = new Employee();
        
        if (employee.isPresent()) {
            dataEmployee = employee.get();
            if (employeeDTO.getId() != null) {
                dataEmployee.setId(employeeDTO.getId());
            }
            if (employeeDTO.getName() != null) {
                dataEmployee.setName(employeeDTO.getName());
            }
            if (employeeDTO.getCpf() != null) {
                dataEmployee.setCpf(employeeDTO.getCpf());
            }
            employeeRepository.save(dataEmployee);
            return "Employee " + dataEmployee.getId() + " successfully updated!"; 
        }
        throw new EmployeeException("Employee " + dataEmployee.getId() + " was not updated. Please, try again." );
    }

    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDto> listAll(){
    List<Employee> employee = employeeRepository.findAll();
    List<EmployeeDto> listEmployee = new ArrayList<>();
         for (Employee employees : employee){
        EmployeeDto employeeDTO = employeeToDTO(employees);
        listEmployee.add(employeeDTO);
    }
        return listEmployee;
    }
    
}