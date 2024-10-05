package com.reevaluation.Department.Microservice.controller;

import com.reevaluation.Department.Microservice.dto.DepartmentDTO;
import com.reevaluation.Department.Microservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private DepartmentController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }
    @PostMapping("/post")
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO)
    {
        DepartmentDTO savedDepartmentDTO = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(savedDepartmentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/{code}")
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable("code") String code)
    {
        DepartmentDTO getDepartmentDTO = departmentService.getDepartment(code);
        return new ResponseEntity<>(getDepartmentDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments()
    {
        List<DepartmentDTO> getDepartmentDTOs = departmentService.getAllDepartments();
        return new ResponseEntity<>(getDepartmentDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteDepartment( @PathVariable("code") String code)
    {
        departmentService.deleteDepartment(code);
        return new ResponseEntity<>("department deleted successfully "+ code ,HttpStatus.OK);
    }
}
