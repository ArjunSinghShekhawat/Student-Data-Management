package com.students.models;

import com.students.enums.GENDER;
import com.students.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {

    @Id
    private ObjectId id;

    private String fullName;

    private String email;

    private String contactNumber;

    private GENDER gender;

    private ROLE role;

    private String password;

    private LocalDate dateOfBirth;

    private String imageUrl;

    @Indexed(unique = true)
    private String rollNumber;

    @DBRef
    private Address address;

    @DBRef
    private Guardian guardian;

    @DBRef
    private AcademicDetails academicDetails;
}
