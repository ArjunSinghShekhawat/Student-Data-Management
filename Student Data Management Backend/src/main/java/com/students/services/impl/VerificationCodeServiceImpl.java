package com.students.services.impl;

import com.students.models.VerificationCode;
import com.students.repositories.VerificationCodeRepository;
import com.students.services.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationCodeServiceImpl implements VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;

    @Override
    public VerificationCode findByEmail(String email) {
        return null;
    }
}
