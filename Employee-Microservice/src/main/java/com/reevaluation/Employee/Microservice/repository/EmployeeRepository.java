package com.reevaluation.Employee.Microservice.repository;


import com.reevaluation.Employee.Microservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmail(String email);
}
