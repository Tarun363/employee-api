package com.infy.employee;

import com.infy.employee.dto.EmployeeDTO;
import com.infy.employee.entity.Employee;
import com.infy.employee.exception.GlobalException;
import com.infy.employee.repository.EmployeeRepository;
import com.infy.employee.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class EmployeeApplicationTests {

    Employee employee = new Employee();
    EmployeeDTO employeeDTO = new EmployeeDTO();
    List<Employee> employeeList = new ArrayList<>();


    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employee.setId(1);
        employee.setName("Test");
        employee.setDesignation("SE");
        employee.setSalary(25000);

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setName("Test 1");
        employee2.setDesignation("SEE");
        employee2.setSalary(65000);

        employeeDTO.setName(employee.getName());
        employeeDTO.setDesignation(employee.getDesignation());
        employeeDTO.setSalary(employee.getSalary());

        employeeList.add(employee);
        employeeList.add(employee2);
    }

    @Test
    void addEmployeeValidTest() {
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        Employee savedEmployee = employeeService.addEmployee(employeeDTO);
        Assertions.assertEquals(savedEmployee.getId(), 1);
    }

    @Test
    void addEmployeeInValidNameTest() {
        employeeDTO.setName(null);
        Assertions.assertThrowsExactly(GlobalException.class, () -> employeeService.addEmployee(employeeDTO), "Invalid employee name");
    }

    @Test
    void addEmployeeInValidDesignationTest() {
        employeeDTO.setDesignation(null);
        Assertions.assertThrowsExactly(GlobalException.class, () -> employeeService.addEmployee(employeeDTO), "Invalid employee designation");
    }

    @Test
    void addEmployeeInValidSalaryTest() {
        employeeDTO.setSalary(0);
        Assertions.assertThrowsExactly(GlobalException.class, () -> employeeService.addEmployee(employeeDTO), "Invalid employee salary");
    }

    @Test
    void getEmployeeByIdValidTest() {
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(employee);
        Mockito.when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Employee employee1 = employeeService.getEmployeeById(1);
        Assertions.assertNotNull(employee1);
    }

    @Test
    void getEmployeeByIdInValidTest() {
        Mockito.when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Assertions.assertThrowsExactly(GlobalException.class, () -> employeeService.getEmployeeById(1), "Employee doesn't exists");
    }

    @Test
    void getAllEmployeesTest() {
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> employeeList1 = employeeService.getAllEmployees();
        Assertions.assertEquals(employeeList1.size(), 2);
    }

    @Test
    void updateEmployeeTest() {
        Mockito.when(employeeRepository.save(Mockito.any())).thenReturn(employee);
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(employee);
        Mockito.when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Assertions.assertNotNull(employeeService.updateEmployee(1, employeeDTO));
    }

    @Test
    void getEmployeesSalaryBetweenResultTest() {
        Mockito.when(employeeRepository.findBySalaryBetweenOrderByNameDesc(Mockito.anyLong(), Mockito.anyLong())).thenReturn(employeeList);
        List<Employee> employeeList1 = employeeService.getEmployeesSalaryBetween(10000, 70000);
        Assertions.assertEquals(employeeList1.size(), 2);
    }

    @Test
    void removeEmployee() {
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(employee);
        Mockito.when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
        employeeService.removeEmployee(Mockito.anyLong());
    }

    @Test
    void taxDeductionForEmployeeDefaultTest() {
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(employee);
        Mockito.when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Assertions.assertEquals(employeeService.taxDeductionForEmployee(Mockito.anyLong()), 0);
    }
    @Test
    void taxDeductionForEmployeeTest() {
        employee.setSalary(65000);
        Mockito.when(employeeRepository.findById(Mockito.anyLong())).thenReturn(employee);
        Mockito.when(employeeRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Assertions.assertNotEquals(employeeService.taxDeductionForEmployee(Mockito.anyLong()), 0);
    }

}
