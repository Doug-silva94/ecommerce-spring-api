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
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;


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

    public void saveEmployee(EmployeeDto employeeDTO) {
        Employee employee = dtoToEmployee(employeeDTO);
        employeeRepository.save(employee);
    }

    public Employee getEmployeeOrElseThrow(Long id) throws EmployeeException {
        return this.employeeRepository.findById(id).orElseThrow(EmployeeException::new);
    }



    public EmployeeDto findOneEmployee(Long id) throws EmployeeException {
        var ex = new EmployeeException(new EmployeeException());
        return employeeToDTO(this.getEmployeeOrElseThrow(id));
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
        return employeeRepository.findAll()
                .stream()
                .map(this::employeeToDTO)
                .collect(Collectors.toList());
    }
}
