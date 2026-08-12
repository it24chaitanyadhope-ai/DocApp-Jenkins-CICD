package com.example.Repository;


import com.example.DocApp.Model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
}
