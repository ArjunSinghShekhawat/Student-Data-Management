package com.students.controllers;

import com.students.enums.ROLE;
import com.students.requests.LoginRequest;
import com.students.requests.OtpVerificationRequest;
import com.students.requests.SignUpRequest;
import com.students.responces.ApiResponse;
import com.students.responces.AuthResponse;
import com.students.services.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sent/otp")
    public ResponseEntity<ApiResponse>sendVerificationMailHelper(@Valid @RequestBody OtpVerificationRequest request) throws MessagingException {

        authService.sendOtp(request.getEmail());

        //send response
        ApiResponse response=new ApiResponse();
        response.setMessage("Otp Sent Successfully !");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signUpHandler(
            @Valid @RequestBody SignUpRequest req) throws Exception {
        String token = authService.signUp(req);

        //send response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Register Success");
        authResponse.setRole(req.getRole());
        authResponse.setStatus(true);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signInHandler(@RequestBody LoginRequest loginRequest) throws Exception {
        AuthResponse authResponse = authService.signIn(loginRequest);
        authResponse.setStatus(true);

        //send response
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
