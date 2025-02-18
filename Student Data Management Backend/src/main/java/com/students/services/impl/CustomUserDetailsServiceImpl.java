package com.students.services.impl;

import com.students.enums.ROLE;
import com.students.models.Faculty;
import com.students.repositories.FacultyRepository;
import com.students.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    private static final String FACULTY_PREFIX = "faculty_";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username.startsWith(FACULTY_PREFIX)){
            String actualUsername = username.substring(FACULTY_PREFIX.length());
            Faculty faculty = facultyRepository.findByEmail(actualUsername);

            if(faculty!=null){
                return buildUserDetails(faculty.getEmail(), faculty.getPassword(), faculty.getRole());
            }
        }else {
            com.students.models.User student = userRepository.findByEmail(username);
            if (student != null) {
                return buildUserDetails(student.getEmail(), student.getPassword(), student.getRole());
            }
        }
        throw new UsernameNotFoundException("Faculty or Student not found with email - " + username);
    }

    private UserDetails buildUserDetails(String email, String password, ROLE role) {
        if (role == null) role = ROLE.ROLE_STUDENT;

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new User(email, password, authorities);
    }
}
