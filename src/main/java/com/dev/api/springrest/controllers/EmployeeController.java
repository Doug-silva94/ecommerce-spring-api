package com.dev.api.springrest.controllers;

import com.dev.api.springrest.dtos.EmployeeDto;
import com.dev.api.springrest.exceptions.EmployeeException;
import com.dev.api.springrest.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {this.employeeService = employeeService;}

    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> listAll() {
        return ResponseEntity.ok(employeeService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable long id) throws EmployeeException {
        return ResponseEntity.ok(employeeService.findOneEmployee(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody EmployeeDto employeeDTO){
        employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeDto employeeDTO){
        employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
