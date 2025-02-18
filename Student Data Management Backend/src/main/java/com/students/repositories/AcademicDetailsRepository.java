package com.students.repositories;

import com.students.models.AcademicDetails;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicDetailsRepository extends MongoRepository<AcademicDetails, ObjectId>{
}
