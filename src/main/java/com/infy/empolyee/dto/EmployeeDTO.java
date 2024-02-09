package com.infy.empolyee.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmployeeDTO {

    private String name;
    private String designation;
    private double salary;
}
