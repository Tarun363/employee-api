package com.infy.empolyee.controller;

import com.infy.empolyee.dto.EmployeeDTO;
import com.infy.empolyee.entity.Employee;
import com.infy.empolyee.exception.GlobalException;
import com.infy.empolyee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO) throws GlobalException {
        return new ResponseEntity<>(employeeService.addEmployee(employeeDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) throws GlobalException {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() throws GlobalException {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @RequestBody EmployeeDTO employeeDTO) throws GlobalException {
        employeeService.updateEmployee(id, employeeDTO);
        return new ResponseEntity<>("Employee updated successfully!", HttpStatus.OK);
    }

    @GetMapping("/salary/{min}/{max}")
    public ResponseEntity<List<Employee>> getEmployeesSalaryBetween(@PathVariable long min, @PathVariable long max) throws GlobalException {
        return new ResponseEntity<>(employeeService.getEmployeesSalaryBetween(min, max), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeEmployee(@PathVariable long id) throws GlobalException {
        employeeService.removeEmployee(id);
        return new ResponseEntity<>("Employee deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/tax/{id}")
    public ResponseEntity<Double> taxDeductionForEmployee(@PathVariable long id) throws GlobalException {
        return new ResponseEntity<>(employeeService.taxDeductionForEmployee(id), HttpStatus.OK);
    }

}
