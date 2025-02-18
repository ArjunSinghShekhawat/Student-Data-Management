package com.students.repositories;

import com.students.models.Faculty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends MongoRepository<Faculty, ObjectId> {

    Faculty findByEmail(String email);
}
