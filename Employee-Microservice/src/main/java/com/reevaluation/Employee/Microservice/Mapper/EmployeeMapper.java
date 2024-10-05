package com.reevaluation.Employee.Microservice.Mapper;

import com.reevaluation.Employee.Microservice.dto.EmployeeDTO;
import com.reevaluation.Employee.Microservice.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail(),
                employee.getDepartmentcode(),
                employee.getAccountcode()
        );
        return employeeDTO;
    }

    public static Employee mapToEmployee(EmployeeDTO employeeDTO)
    {
        Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstname(),
                employeeDTO.getLastname(),
                employeeDTO.getEmail(),
                employeeDTO.getDepartmentcode(),
                employeeDTO.getAccountcode()
        );
        return employee;
    }
}
