package com.infy.employee.service;

import com.infy.employee.dto.EmployeeDTO;
import com.infy.employee.entity.Employee;
import com.infy.employee.exception.GlobalException;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(EmployeeDTO employeeDTO) throws GlobalException;

    Employee getEmployeeById(long id) throws GlobalException;

    List<Employee> getAllEmployees() throws GlobalException;

    Employee updateEmployee(long id, EmployeeDTO employeeDTO) throws GlobalException;

    List<Employee> getEmployeesSalaryBetween(long min, long max);

    void removeEmployee(long id);

    double taxDeductionForEmployee(long id);
}
