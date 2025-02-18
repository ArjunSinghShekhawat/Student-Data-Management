package com.students.models;

import com.students.enums.COURSE;
import com.students.enums.GENDER;
import com.students.enums.ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Faculty {

    @Id
    private ObjectId id;

    private String fullName;

    private GENDER gender;

    private LocalDate dob;

    private String email;

    private String password;

    private String contactNumber;

    private String plotNumber;

    private String street;

    private int experience;

    private COURSE qualification;

    private ROLE role;

    private String imageUrl;
}
