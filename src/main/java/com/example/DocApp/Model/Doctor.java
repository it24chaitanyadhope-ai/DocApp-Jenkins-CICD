package com.example.DocApp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Doctor")
public class Doctor {
    @Id
    private String id;
    private String name;
    private String specialization;
    private String location;
    private String contact;

    // Default constructor
    public Doctor() {}

    // Constructor with parameters
    public Doctor(String name, String specialization, String location, String contact) {
        this.name = name;
        this.specialization = specialization;
        this.location = location;
        this.contact = contact;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}