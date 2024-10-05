package com.reevaluation.Employee.Microservice.controller;


import com.reevaluation.Employee.Microservice.dto.APIResponseDTO;
import com.reevaluation.Employee.Microservice.dto.EmployeeDTO;
import com.reevaluation.Employee.Microservice.entity.Employee;
import com.reevaluation.Employee.Microservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
        public class EmployeeController {

            @Autowired
            private EmployeeService employeeService;

            private EmployeeController(EmployeeService employeeService ){
                this.employeeService=employeeService;
            }
            @PostMapping("/post_employee")
            public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO)
            {
                EmployeeDTO savedEmployeeDTO = employeeService.saveemployee(employeeDTO);
        return new ResponseEntity<>(savedEmployeeDTO , HttpStatus.CREATED);
    }


    @GetMapping("/get_employee/{email}")
    public ResponseEntity<APIResponseDTO> getEmployee(@PathVariable("email") String email)
    {
        APIResponseDTO getapiResponseDTO = employeeService.getEmployee(email);
        return new ResponseEntity<>(getapiResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/get_employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees()
    {
        List<EmployeeDTO> getEmployeeDTOs = employeeService.getAllEmployee();
        return new ResponseEntity<>(getEmployeeDTOs, HttpStatus.OK);
    }

    @DeleteMapping("/delete_employees/{email}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("email") String email)
    {
         employeeService.deleteEmployee(email);
        return new ResponseEntity<>("Successfully deleted following employee record" + email, HttpStatus.OK);
    }
}
