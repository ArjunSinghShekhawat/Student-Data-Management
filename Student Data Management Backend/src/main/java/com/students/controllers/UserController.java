package com.students.controllers;

import com.students.exceptions.StudentException;
import com.students.jwt.JwtProvider;
import com.students.models.User;
import com.students.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;


    @GetMapping("/get")
    public ResponseEntity<User>getUserByEmail(@RequestHeader("Authorization") String jwt) throws StudentException {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        User savedUser = userService.findByUserEmail(email);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }
}
