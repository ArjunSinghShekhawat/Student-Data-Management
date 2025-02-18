package com.students.repositories;

import com.students.models.Guardian;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuardianRepository extends MongoRepository<Guardian, ObjectId> {
}
