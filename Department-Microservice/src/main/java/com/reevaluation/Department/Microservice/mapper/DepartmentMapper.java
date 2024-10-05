package com.reevaluation.Department.Microservice.mapper;

import com.reevaluation.Department.Microservice.dto.DepartmentDTO;
import com.reevaluation.Department.Microservice.entity.Department;

public class DepartmentMapper {
    public static DepartmentDTO mapToDepartmentDto(Department department)
    {
        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getId(),
                department.getDepartmentname(),
                department.getDepartmentdescription(),
                department.getDepartmentcode()
        );
        return departmentDTO;
    }

    public static Department mapToDepartment(DepartmentDTO departmentDTO)
    {
        Department department = new Department(
                departmentDTO.getId(),
                departmentDTO.getDepartmentname(),
                departmentDTO.getDepartmentdescription(),
                departmentDTO.getDepartmentcode()
        );
        return department;
    }
}
