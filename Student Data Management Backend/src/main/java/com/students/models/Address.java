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
public class Address {

    @Id
    private ObjectId id;
    private String plotNumber;
    private String street;
    private String city;
    private String state;
    private String zipCode;

    @DBRef
    private User user;
}
