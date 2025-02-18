package com.students.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-checker")
public class HealthChecker {


    @GetMapping("/ok")
    public ResponseEntity<String>healthChecker(){
        return new ResponseEntity<>("Student Data Management Health Check", HttpStatus.OK);
    }
}
