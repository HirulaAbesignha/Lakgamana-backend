package com.seproject.lakgamana_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Passenger")
public class Passenger {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Integer passengerId;
    
    @NotNull
    @Size(max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;
    
    @Min(0)
    @Column(name = "age")
    private Integer age;
    
    @Size(max = 15)
    @Column(name = "contact_num", length = 15)
    private String contactNum;
    
    @Email
    @Size(max = 100)
    @Column(name = "email", unique = true, length = 100)
    private String email;
    
    public enum Gender {
        Male, Female, Other
    }
    
    // Default constructor
    public Passenger() {}
    
    // Constructor with parameters
    public Passenger(String name, Gender gender, Integer age, String contactNum, String email) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.contactNum = contactNum;
        this.email = email;
    }
    
    // Getters and Setters
    public Integer getPassengerId() {
        return passengerId;
    }
    
    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Gender getGender() {
        return gender;
    }
    
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public String getContactNum() {
        return contactNum;
    }
    
    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}