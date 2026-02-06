package com.medislot.medislot_backend.service;

import com.medislot.medislot_backend.entity.Patient;
import com.medislot.medislot_backend.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        // Using Java 8 Streams to satisfy your JD requirements
        return patientRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
}