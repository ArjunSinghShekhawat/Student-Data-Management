package com.students.controllers;

import com.students.models.Faculty;
import com.students.models.User;
import com.students.services.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final FacultyService facultyService;

    @GetMapping("/all/students")
    public ResponseEntity<List<User>>getAllStudents(){
        List<User>allStudents=facultyService.getAllStudents();
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }
    @GetMapping("/all/faculty")
    public ResponseEntity<List<Faculty>>getAllFaculty(){
        List<Faculty>allFaculty=facultyService.getAllFaculty();
        return new ResponseEntity<>(allFaculty, HttpStatus.OK);
    }
}
