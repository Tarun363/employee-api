package com.infy.empolyee.repository;

import com.infy.empolyee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findById(long id);

    List<Employee> findBySalaryBetweenOrderByNameDesc(long min, long max);
}
