package com.students.services;

import com.students.exceptions.FacultyException;
import com.students.exceptions.StudentException;
import com.students.models.Faculty;
import com.students.models.User;
import org.bson.types.ObjectId;

public interface FacultyService {

    Faculty findByEmail(String email) throws FacultyException;
    Faculty findById(ObjectId id) throws FacultyException;
    User findByRollNumberStudent(String rollNumber) throws StudentException;
    User updateStudentInformation(String rollNumber,User user) throws StudentException;
    User createNewUserEntry(User user);
}
