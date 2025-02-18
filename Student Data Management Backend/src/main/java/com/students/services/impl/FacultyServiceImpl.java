package com.students.services.impl;

import com.students.exceptions.FacultyException;
import com.students.exceptions.StudentException;
import com.students.models.Faculty;
import com.students.models.User;
import com.students.repositories.FacultyRepository;
import com.students.repositories.UserRepository;
import com.students.services.FacultyService;
import com.students.utility.Validate;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;

    @Override
    public Faculty findByEmail(String email) throws FacultyException {
        Faculty faculty = facultyRepository.findByEmail(email);

        if(faculty==null){
            throw new FacultyException("Faculty not found with email "+email);
        }
        return facultyRepository.findByEmail(email);
    }

    @Override
    public Faculty findById(ObjectId id) throws FacultyException {
        return facultyRepository.findById(id).orElseThrow(()->new FacultyException("Faculty not found with id "+id));
    }

    @Override
    public User findByRollNumberStudent(String rollNumber) throws StudentException {
        User student = userRepository.findByRollNumber(rollNumber);

        if(student==null){
            throw new StudentException("student not found with roll number "+rollNumber);
        }
        return userRepository.findByRollNumber(rollNumber);
    }

    //pending..............

    @Override
    public User updateStudentInformation(String rollNumber, User user) throws StudentException {

       User student =  findByRollNumberStudent(rollNumber);
        //update user
        if(user.getFullName()!=null){
            student.setFullName(Validate.validate(user.getFullName()));
        }
        return userRepository.save(user);
    }

    @Override
    public User createNewUserEntry(User user) {
        return null;
    }
}
