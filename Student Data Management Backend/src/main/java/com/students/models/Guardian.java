package com.students.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Guardian {

    @Id
    private ObjectId id;
    private String guardianName;
    private String relationship;
    private String contactNumber;
    private String occupation;
    @DBRef
    private User user;
}
