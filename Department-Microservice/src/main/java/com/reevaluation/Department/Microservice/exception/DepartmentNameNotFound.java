package com.reevaluation.Department.Microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DepartmentNameNotFound extends RuntimeException{

 private String departmentname;
 private String departmentdescription;
 private String departmentcode;

 public DepartmentNameNotFound( String departmentname,String departmentdescription,String departmentcode)
 {
    super(String.format("%s with %s not found:%s",departmentname,departmentdescription,departmentcode));

    this.departmentname=departmentname;
    this.departmentdescription=departmentdescription;
     this.departmentcode=departmentcode;
 }
}
