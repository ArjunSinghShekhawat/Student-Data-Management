package com.students.services;

import com.students.models.VerificationCode;

public interface VerificationCodeService {

    VerificationCode findByEmail(String email);
}
