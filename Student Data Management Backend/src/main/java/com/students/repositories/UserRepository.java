package com.students.repositories;

import com.students.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{

    User findByEmail(String email);
    User findByRollNumber(String rollNumber);
}
