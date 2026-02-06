package com.medislot.medislot_backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //Many appointments for one doctor
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne //Many appointments for one patient
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime appointmentTime;
    private String status; // e.g., booked, cancelled, etc.

}