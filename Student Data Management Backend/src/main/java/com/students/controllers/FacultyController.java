package com.students.controllers;

import com.students.exceptions.FacultyException;
import com.students.exceptions.StudentException;
import com.students.jwt.JwtProvider;
import com.students.models.Faculty;
import com.students.models.User;
import com.students.services.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faculty")
@RequiredArgsConstructor
public class FacultyController {

    private final FacultyService facultyService;
    private final JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity<Faculty> getFacultyByEmail(@RequestHeader("Authorization") String jwt) throws FacultyException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Faculty savedFaculty = facultyService.findByEmail(email);
        return new ResponseEntity<>(savedFaculty, HttpStatus.OK);
    }
    @GetMapping("/student/{rollNumber}")
    public ResponseEntity<User>findUserByRollNumber(String rollNumber) throws StudentException {
        User savedUser = facultyService.findByRollNumberStudent(rollNumber);
        if(savedUser==null){
            throw new StudentException("Student not fount with roll number "+rollNumber);
        }
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
    @PutMapping("/student/{rollNumber}")
    public ResponseEntity<User>updateStudentInformation(String rollNumber,@RequestBody User user) throws StudentException {
        User savedUser = facultyService.updateStudentInformation(rollNumber,user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
    @PostMapping("/student")
    public ResponseEntity<User>createNewUser(@RequestBody User user){
        User newUser = facultyService.createNewUserEntry(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllStudent() {
        List<User>allStudent = facultyService.getAllStudents();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }
    @PutMapping("/{rollNumber}")
    public ResponseEntity<Faculty>updateSelfInformation(@RequestHeader("Authorization") String jwt,@RequestBody Faculty faculty) throws FacultyException {
        Faculty savedFaculty = facultyService.updateSelfInformation(jwt,faculty);
        return new ResponseEntity<>(savedFaculty, HttpStatus.OK);
    }
}
