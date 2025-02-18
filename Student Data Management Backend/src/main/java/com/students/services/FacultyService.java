package com.students.services;

import com.students.exceptions.FacultyException;
import com.students.exceptions.StudentException;
import com.students.models.Faculty;
import com.students.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface FacultyService {

    Faculty findByEmail(String email) throws FacultyException;
    Faculty findById(ObjectId id) throws FacultyException;
    User findByRollNumberStudent(String rollNumber) throws StudentException;
    User updateStudentInformation(String rollNumber,User user) throws StudentException;
    User createNewUserEntry(User user);
    List<User>getAllStudents();
    Faculty updateSelfInformation(String jwt,Faculty faculty) throws FacultyException;
    List<Faculty>getAllFaculty();
}
