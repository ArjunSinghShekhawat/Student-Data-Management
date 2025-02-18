package com.students.services.impl;

import com.students.enums.ROLE;
import com.students.exceptions.StudentException;
import com.students.jwt.JwtProvider;
import com.students.models.*;
import com.students.repositories.*;
import com.students.requests.LoginRequest;
import com.students.requests.SignUpRequest;
import com.students.responces.AuthResponse;
import com.students.services.AuthService;
import com.students.utility.GenerateOtp;
import com.students.utility.Validate;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailServiceImpl emailService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final AuthenticationManager authenticationManager;
    private final AddressRepository addressRepository;
    private final AcademicDetailsRepository academicDetailsRepository;
    private final GuardianRepository guardianRepository;
    private final FacultyRepository facultyRepository;


    @Override
    public void sendOtp(String email) throws MessagingException {

        //email validate
        email = Validate.validate(email);

        String SIGNING_PREFIX = "signing_";

        //check user already present or not 
        if(email.startsWith(SIGNING_PREFIX)){
            email=email.substring(SIGNING_PREFIX.length());
            userRepository.findByEmail(email);
        }

        //check otp is already available or not if otp is already available then remove it and generate new otp
        VerificationCode isExist = verificationCodeRepository.findByEmail(email);

        if(isExist!=null){
            verificationCodeRepository.delete(isExist);
        }
        
        //generate new otp
        String otp = GenerateOtp.generateOtp();
        
        //create verification code 
        VerificationCode verificationCode=new VerificationCode();
        
        verificationCode.setEmail(email);
        verificationCode.setOtp(otp);
        
        //Save VerificationCodeS
        VerificationCode saveVerificationCode = verificationCodeRepository.save(verificationCode);

        String subject="Data Store bazaar";
        String text = "Send SignIn/SignUp Verification Code "+otp;

        //send otp verification email
        emailService.sendVerificationMail(email,subject,text);
    }

    @Override
    public String signUp(SignUpRequest request) throws StudentException {

        //fetch all data from request
        String email = Validate.validate(request.getEmail());
        String fullName = Validate.validate(request.getFullName());
        String otp=request.getOtp();
        ROLE role = request.getRole();

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);

        //verify student otp correct oe not
        if(verificationCode==null || !verificationCode.getOtp().equals(otp)){
            throw new StudentException("wrong otp...");
        }

        if(role.toString().equals("ROLE_STUDENT")){
            User user = userRepository.findByEmail(email);

            //create a new student
            if(user==null){

                User student=new User();

                student.setEmail(email);
                student.setPassword(passwordEncoder.encode(Validate.validate(request.getPassword())));
                student.setFullName(Validate.validate(fullName));
                student.setGender(request.getGender());
                student.setContactNumber(request.getContactNumber());
                student.setRole(request.getRole());

                User savedStudent = userRepository.save(student);

                //address
                Address address=new Address();
                address.setUser(savedStudent);
                addressRepository.save(address);

                //academicDetails
                AcademicDetails academicDetails=new AcademicDetails();
                academicDetails.setUser(savedStudent);
                academicDetailsRepository.save(academicDetails);

                //guardian
                Guardian guardian = new Guardian();
                guardian.setUser(savedStudent);
                guardianRepository.save(guardian);
            }
        }else{
            Faculty faculty = facultyRepository.findByEmail(email);
            //create a new student
            if(faculty==null){

                Faculty teacher = new Faculty();

                teacher.setEmail(email);
                teacher.setPassword(passwordEncoder.encode(Validate.validate(request.getPassword())));
                teacher.setFullName(Validate.validate(fullName));
                teacher.setGender(request.getGender());
                teacher.setContactNumber(request.getContactNumber());
                teacher.setRole(request.getRole());

                Faculty savedFaculty = facultyRepository.save(teacher);
            }
        }


        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(
                role.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                email, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate jwt token
        return jwtProvider.generateToken(authentication);
    }

    @Override
    public AuthResponse signIn(LoginRequest request) throws Exception {

        //fetch request data
        String email = Validate.validate(request.getEmail());
        String otp = request.getOtp();

        //check password of the user
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email,request.getPassword()));

        //authenticate user
        Authentication authentication = authenticate(email, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        //generate jwt token
        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        //return response
        authResponse.setMessage("Login Success");
        authResponse.setJwt(token);

        //fetch student role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        authResponse.setRole(ROLE.valueOf(roleName));

        return authResponse;
    }
    private Authentication authenticate(String username, String otp) throws Exception {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        //check a student available or not in database
        if (userDetails == null) {
            System.out.println("sign in userDetails - null ");
            throw new BadCredentialsException("Invalid username or password");
        }
        //otp find in database
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(userDetails.getUsername());

        //verify student otp correct or not
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("wrong otp...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
