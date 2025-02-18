package com.students.services.impl;

import com.students.exceptions.StudentException;
import com.students.models.User;
import com.students.repositories.UserRepository;
import com.students.services.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findByUserEmail(String email) throws StudentException {

        User student = userRepository.findByEmail(email);
        if(student!=null){
            return student;
        }
        throw new StudentException("Student not found with email "+email);
    }

    @Override
    public User findById(ObjectId id) throws StudentException {
        return userRepository.findById(id).orElseThrow(()->new StudentException("Student not found with id "+id));
    }
}
