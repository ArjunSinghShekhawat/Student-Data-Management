package com.students.services;

import com.students.exceptions.StudentException;
import com.students.models.User;
import org.bson.types.ObjectId;

public interface UserService {

    User findByUserEmail(String email) throws StudentException;
    User findById(ObjectId id) throws StudentException;


}
