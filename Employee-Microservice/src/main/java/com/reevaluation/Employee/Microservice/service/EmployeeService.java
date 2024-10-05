package com.reevaluation.Employee.Microservice.service;


import com.reevaluation.Employee.Microservice.Mapper.EmployeeMapper;
import com.reevaluation.Employee.Microservice.dto.APIResponseDTO;
import com.reevaluation.Employee.Microservice.dto.AccountDTO;
import com.reevaluation.Employee.Microservice.dto.DepartmentDTO;
import com.reevaluation.Employee.Microservice.dto.EmployeeDTO;
import com.reevaluation.Employee.Microservice.entity.Employee;
import com.reevaluation.Employee.Microservice.feignclient.APIClient;
import com.reevaluation.Employee.Microservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    private EmployeeRepository employeeRepository;

    //@Autowired
   //private RestTemplate restTemplate;
    private WebClient webClient;
    private APIClient apiClient;

    public EmployeeDTO saveemployee(EmployeeDTO employeeDTO)
    {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);

        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO savedEmployeeDTO = EmployeeMapper.mapToEmployeeDTO(savedEmployee);
        return savedEmployeeDTO;
    }

   // @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public APIResponseDTO getEmployee(String email)
    {
        logger.info("inside getEmployee method");
        Employee getemployee = employeeRepository.findByEmail(email);

      /*  ResponseEntity<DepartmentDTO> responseEntity =restTemplate.getForEntity("http://localhost:8080/api/records/get/"
                        + getemployee.getDepartmentcode(), DepartmentDTO.class);

       DepartmentDTO getDepartmentDTO = responseEntity.getBody();*/

        DepartmentDTO departmentDTO = webClient.get()
                .uri("http://localhost:8080/api/records/get/" + getemployee.getDepartmentcode())
                .retrieve()
                .bodyToMono(DepartmentDTO.class)
                .block();
        AccountDTO accountDTO = webClient.get()
                .uri("http://localhost:8083/api/account/get/" + getemployee.getAccountcode())
                .retrieve()
                .bodyToMono(AccountDTO.class)
                .block();

        //DepartmentDTO getDepartmentDTO = apiClient.getDepartment(getemployee.getDepartmentcode());
        EmployeeDTO getEmployeeDTO = EmployeeMapper.mapToEmployeeDTO(getemployee);

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(getEmployeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);
        apiResponseDTO.setAccount(accountDTO);
        return apiResponseDTO;
    }

    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> getEmployees = employeeRepository.findAll();
        List<EmployeeDTO> getEmployeeDTOs = new ArrayList<>();
        for( Employee employee : getEmployees){
            EmployeeDTO employeeDTO = new EmployeeDTO(
                    employee.getId(),
                    employee.getFirstname(),
                    employee.getLastname(),
                    employee.getEmail(),
                    employee.getDepartmentcode(),
                    employee.getAccountcode());
            getEmployeeDTOs.add(employeeDTO);
        }
        return getEmployeeDTOs;
    }

    public void deleteEmployee(String email) {
        Employee getRecord =  employeeRepository.findByEmail(email);
        employeeRepository.delete(getRecord);
    }
    public APIResponseDTO getDefaultDepartment(String email,Exception exception){
        logger.info("inside defaultDepartment");
        Employee getemployee = employeeRepository.findByEmail(email);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setDepartmentcode("D001");
        departmentDTO.setDepartmentname("Default Department");
        departmentDTO.setDepartmentdescription("Default Department Description");

        EmployeeDTO getEmployeeDTO = new EmployeeDTO(
                getemployee.getId(),
                getemployee.getFirstname(),
                getemployee.getLastname(),
                getemployee.getEmail(),
                getemployee.getDepartmentcode(),
                getemployee.getAccountcode()
        );

        APIResponseDTO apiResponseDTO = new APIResponseDTO();
        apiResponseDTO.setEmployee(getEmployeeDTO);
        apiResponseDTO.setDepartment(departmentDTO);
        return apiResponseDTO;
    }

}
