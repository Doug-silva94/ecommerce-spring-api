package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.EmployeeDto;
import com.dev.api.springrest.exceptions.EmployeeException;
import com.dev.api.springrest.models.Employee;
import com.dev.api.springrest.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    
	@Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDto employeeToDTO(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setName(employee.getName());
        employeeDto.setCpf(employee.getCpf());

        return employeeDto;
    }

    public Employee dtoToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setCpf(employeeDto.getCpf().replace(".", "").replace("-", ""));

        return employee;
    }

    public void saveEmployee(EmployeeDto employeeDto) {
        Employee employee = dtoToEmployee(employeeDto);
        employeeRepository.save(employee);
    }

    public Employee getEmployeeOrElseThrow(Long id) throws EmployeeException {
        return this.employeeRepository.findById(id).orElseThrow(EmployeeException::new);
    }

    public EmployeeDto findOneEmployee(Long id) throws EmployeeException {
        var ex = new EmployeeException(new EmployeeException());
        return employeeToDTO(this.getEmployeeOrElseThrow(id));
    }

    public void updateEmployee(Long id, EmployeeDto employeeDto) {
        Optional<Employee> employee = employeeRepository.findById(id);
        Employee employeeOnBank = new Employee();
        if (employee.isPresent()) {
            employeeOnBank = employee.get();
            if (employeeDto.getName() != null) {
                employeeOnBank.setName(employeeDto.getName());
            }
            if (employeeDto.getCpf() != null) {
                employeeOnBank.setCpf(employeeDto.getCpf());
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
