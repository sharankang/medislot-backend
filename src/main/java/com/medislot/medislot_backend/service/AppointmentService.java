package com.medislot.medislot_backend.service;

import com.medislot.medislot_backend.entity.Appointment;
import com.medislot.medislot_backend.exception.ResourceNotFoundException;
import com.medislot.medislot_backend.repository.AppointmentRepository;
import com.medislot.medislot_backend.repository.DoctorRepository;
import com.medislot.medislot_backend.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private NotificationService notificationService;

    public Appointment bookAppointment(Appointment appointment) {
        // doctor check
        doctorRepository.findById(appointment.getDoctor().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + appointment.getDoctor().getId()));

        // patient check
        patientRepository.findById(appointment.getPatient().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + appointment.getPatient().getId()));

        // set initial status
        appointment.setStatus("BOOKED");

        // save and trigger the async notification
        Appointment saved = appointmentRepository.save(appointment);
        notificationService.sendBookingNotification(saved.getPatient().getName());
        
        // return saved object
        return saved;
    }
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    
}

