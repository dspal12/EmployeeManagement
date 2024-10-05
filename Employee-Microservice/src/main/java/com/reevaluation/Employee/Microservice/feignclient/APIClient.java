package com.reevaluation.Employee.Microservice.feignclient;

import com.reevaluation.Employee.Microservice.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="DEPARTMENT-MICROSERVICE")
public interface APIClient {
    @GetMapping("api/department/get/{code}")
    DepartmentDTO getDepartment(@PathVariable("code") String code);

}
