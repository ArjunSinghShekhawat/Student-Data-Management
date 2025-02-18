package com.students.services.impl;

import com.students.exceptions.VerificationException;
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
    public VerificationCode findByEmail(String email) throws VerificationException {
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);

        if(verificationCode==null){
            throw new VerificationException("Verification code not found with email "+email);
        }
        return verificationCode;
    }
}
