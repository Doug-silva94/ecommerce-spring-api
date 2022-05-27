package com.dev.api.springrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dtos.EmployeeDTO;
import com.dev.api.springrest.models.Employee;
import com.dev.api.springrest.repositories.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = dtoToEmployee(employeeDTO);
        employeeRepository.save(employee);
    }

    public EmployeeDTO employeeToDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setCpf(employee.getCpf());
        return employeeDTO;
    }

    public Employee dtoToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();

        employee.setName(employeeDTO.getName());
        employee.setCpf(employeeDTO.getCpf().replace(".", "").replace("-", ""));
        return employee;
    }

    public EmployeeDTO findOneEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employeeOnData;
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if (employee.isPresent()){
            employeeOnData = employee.get();
           employeeDTO = employeeToDTO(employee.get());
        }
        return employeeDTO;
    }

    public void updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employeeOnBank = new Employee();
        if (employee.isPresent()) {
            employeeOnBank = employee.get();
            if (employeeDTO.getName() != null) {
                employeeOnBank.setName(employeeDTO.getName());
            }
            if (employeeDTO.getCpf() != null) {
                employeeOnBank.setCpf(employeeDTO.getCpf());
            }
            employeeRepository.save(employeeOnBank);
        }
    }

    public void deleteEmployee(long id){
        employeeRepository.deleteById(id);
    }

    public List<EmployeeDTO> listAll(){
    List<Employee> employee = employeeRepository.findAll();
    List<EmployeeDTO> listEmployee = new ArrayList<>();
         for (Employee employees : employee){
        EmployeeDTO employeeDTO = employeeToDTO(employees);
        listEmployee.add(employeeDTO);
    }
        return listEmployee;
    }
}
