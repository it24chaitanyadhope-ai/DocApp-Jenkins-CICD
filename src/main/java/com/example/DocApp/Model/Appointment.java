package com.example.DocApp.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "appointments")
public class Appointment {
    @Id
    private String id;
    private String doctorId;
    private String patientName;
    private LocalDateTime appointmentDateTime;

    public Appointment() {}

    public Appointment(String doctorId, String patientName, LocalDateTime appointmentDateTime) {
        this.doctorId = doctorId;
        this.patientName = patientName;
        this.appointmentDateTime = appointmentDateTime;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public LocalDateTime getAppointmentDateTime() { return appointmentDateTime; }
    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) { this.appointmentDateTime = appointmentDateTime; }
}