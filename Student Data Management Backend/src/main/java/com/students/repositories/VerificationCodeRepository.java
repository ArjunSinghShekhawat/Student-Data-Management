package com.students.repositories;

import com.students.models.VerificationCode;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends MongoRepository<VerificationCode, ObjectId> {

    VerificationCode findByEmail(String email);
}
