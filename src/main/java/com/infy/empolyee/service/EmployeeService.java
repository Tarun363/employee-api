package com.infy.empolyee.service;

import com.infy.empolyee.dto.EmployeeDTO;
import com.infy.empolyee.entity.Employee;
import com.infy.empolyee.exception.GlobalException;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(EmployeeDTO employeeDTO) throws GlobalException;

    Employee getEmployeeById(long id) throws GlobalException;

    List<Employee> getAllEmployees() throws GlobalException;

    Employee updateEmployee(long id, EmployeeDTO employeeDTO) throws GlobalException;

    List<Employee> getEmployeesSalaryBetween(long min, long max);
}
