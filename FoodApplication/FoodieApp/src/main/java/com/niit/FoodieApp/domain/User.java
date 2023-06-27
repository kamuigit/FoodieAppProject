package com.niit.FoodieApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private long phoneNo;
    private String password;
    private String confirmPassword;
    private int age;
    private String image;
    private String role;
    private String city;
    private String houseNo;
    private String street;
    private String pinCode;
}
