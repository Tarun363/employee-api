package com.infy.empolyee.service;

import com.infy.empolyee.dto.EmployeeDTO;
import com.infy.empolyee.entity.Employee;
import com.infy.empolyee.exception.GlobalException;
import com.infy.empolyee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(EmployeeDTO employeeDTO) throws GlobalException {
        validateEmployeeDTO(employeeDTO);
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setDesignation(employeeDTO.getDesignation());
        employee.setSalary(employeeDTO.getSalary());
        employee = employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee getEmployeeById(long id) throws GlobalException {
        if (!employeeRepository.existsById(id)) {
            throw new GlobalException("Employee doesn't exists");
        }
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployees() throws GlobalException {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(long id, EmployeeDTO employeeDTO) throws GlobalException {
        Employee employee = getEmployeeById(id);
        if (null != employeeDTO.getName()) {
            employee.setName(employeeDTO.getName());
        }
        if (null != employeeDTO.getDesignation()) {
            employee.setDesignation(employeeDTO.getDesignation());
        }
        if (0 != employeeDTO.getSalary()) {
            employee.setSalary(employeeDTO.getSalary());
        }
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployeesSalaryBetween(long min, long max) {
        return employeeRepository.findBySalaryBetweenOrderByNameDesc(min, max);
    }

    public void validateEmployeeDTO(EmployeeDTO employeeDTO) {
        if (null == employeeDTO.getName() || employeeDTO.getName().isBlank() || employeeDTO.getName().isEmpty()) {
            throw new GlobalException("Invalid employee name");
        }
        if (null == employeeDTO.getDesignation() || employeeDTO.getDesignation().isBlank() || employeeDTO.getDesignation().isEmpty()) {
            throw new GlobalException("Invalid employee designation");
        }
        if (0 == employeeDTO.getSalary()) {
            throw new GlobalException("Invalid employee salary");
        }
    }
}
