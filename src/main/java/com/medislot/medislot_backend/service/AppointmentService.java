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

    public Appointment bookAppointment(Appointment appointment) {
        //Check if doctor exists
        doctorRepository.findById(appointment.getDoctor().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + appointment.getDoctor().getId()));

        //Check if patient exists
        patientRepository.findById(appointment.getPatient().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + appointment.getPatient().getId()));

        appointment.setStatus("BOOKED");
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
}

