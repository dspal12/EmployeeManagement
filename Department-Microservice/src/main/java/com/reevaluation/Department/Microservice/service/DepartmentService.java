package com.reevaluation.Department.Microservice.service;

import com.reevaluation.Department.Microservice.dto.DepartmentDTO;
import com.reevaluation.Department.Microservice.entity.Department;
import com.reevaluation.Department.Microservice.exception.DepartmentNameNotFound;
import com.reevaluation.Department.Microservice.mapper.DepartmentMapper;
import com.reevaluation.Department.Microservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO)
    {
        Department department = DepartmentMapper.mapToDepartment(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentDTO savedDepartmentDTO = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        return savedDepartmentDTO;
    }

    public DepartmentDTO getDepartment(String code)
    {
        Department getRecord = departmentRepository.findByDepartmentcode(code);
        //String check = getRecord.toString();
        if(getRecord == null)
        {throw new DepartmentNameNotFound("Departname","DepartmentDescription",code);
           }
        else
        {  DepartmentDTO getDepartmentDTO = DepartmentMapper.mapToDepartmentDto(getRecord);
            return getDepartmentDTO;
        }

    }

    public List<DepartmentDTO> getAllDepartments(){
        List<Department> getDepartments = departmentRepository.findAll();
        List<DepartmentDTO> getDepartmentDTOs = new ArrayList<>();
        for( Department department : getDepartments){
          DepartmentDTO departmentDTO = new DepartmentDTO(
                  department.getId(),
                  department.getDepartmentname(),
                  department.getDepartmentdescription(),
                  department.getDepartmentcode() );
            getDepartmentDTOs.add(departmentDTO);
        }
        return getDepartmentDTOs;
    }

    public void deleteDepartment(String code) {
        Department getRecord =  departmentRepository.findByDepartmentcode(code);
        if(getRecord == null)
        {throw new DepartmentNameNotFound("Departname","DepartmentDescription",code);
        }
        departmentRepository.delete(getRecord);
    }

}
