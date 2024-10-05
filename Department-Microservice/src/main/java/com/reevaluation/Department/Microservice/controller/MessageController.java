package com.reevaluation.Department.Microservice.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//this annotation is used to refresh the configuration properties at runtime
//we need to call /actuator/refresh to reload the configuration properties.
@RefreshScope
@RestController
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;
    @GetMapping("message")
    public String message() {
        return message;
    }
}
