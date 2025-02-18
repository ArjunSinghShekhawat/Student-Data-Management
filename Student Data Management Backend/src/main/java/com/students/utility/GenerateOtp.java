package com.students.utility;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class GenerateOtp {

    public String generateOtp(){

        StringBuilder otp = new StringBuilder(6);
        Random random=new Random();

        for(int i=0;i<6;++i){
            otp.append(random.nextInt(10));
        }
        return String.valueOf(otp);
    }
}
