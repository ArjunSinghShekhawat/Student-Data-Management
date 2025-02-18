package com.students.models;

import com.students.enums.BRANCH;
import com.students.enums.COURSE;
import com.students.enums.MONTHS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.EnumMap;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicDetails {

    @Id
    private ObjectId id;
    private COURSE course;
    private BRANCH branch;
    private String yearOfAdmission;
    private List<String> semesterRecords;
    private Double cgpa;
    private Double sgpa;
    private EnumMap<MONTHS, Integer> monthlyAttendance;
    private int numberOfBacklog;

    @DBRef
    private User user;

}
