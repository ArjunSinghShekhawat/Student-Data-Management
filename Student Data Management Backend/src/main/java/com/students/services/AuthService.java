package com.students.services;

import com.students.exceptions.StudentException;
import com.students.requests.LoginRequest;
import com.students.requests.SignUpRequest;
import com.students.responces.AuthResponse;
import jakarta.mail.MessagingException;

public interface AuthService {

    void sendOtp(String email) throws MessagingException;
    String signUp(SignUpRequest request) throws StudentException;
    AuthResponse signIn(LoginRequest request) throws Exception;
}
