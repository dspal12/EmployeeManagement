package com.reevaluation.Department.Microservice.repository;

import com.reevaluation.Department.Microservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDepartmentcode(String code);
    //Department deleteByDepartmentcode(String code);
}
