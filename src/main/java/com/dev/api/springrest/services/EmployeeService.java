package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.EmployeeDto;
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

    public void saveEmployee(EmployeeDto employeeDTO) {
        Employee employee = dtoToEmployee(employeeDTO);
        employeeRepository.save(employee);
    }

    public EmployeeDto employeeToDTO(Employee employee){
        EmployeeDto employeeDTO = new EmployeeDto();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setCpf(employee.getCpf());
        return employeeDTO;
    }

    public Employee dtoToEmployee(EmployeeDto employeeDTO){
        Employee employee = new Employee();

        employee.setName(employeeDTO.getName());
        employee.setCpf(employeeDTO.getCpf().replace(".", "").replace("-", ""));
        return employee;
    }

    public EmployeeDto findOneEmployee(Long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employeeOnData;
        EmployeeDto employeeDTO = new EmployeeDto();
        if (employee.isPresent()){
            employeeOnData = employee.get();
           employeeDTO = employeeToDTO(employee.get());
        }
        return employeeDTO;
    }

    public void updateEmployee(Long id, EmployeeDto employeeDTO) {
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
