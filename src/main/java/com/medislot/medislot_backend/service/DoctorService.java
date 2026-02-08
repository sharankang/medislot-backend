package com.medislot.medislot_backend.service;

import com.medislot.medislot_backend.entity.Doctor;
import com.medislot.medislot_backend.exception.ResourceNotFoundException;
import com.medislot.medislot_backend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(Long id, Doctor details) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        doctor.setName(details.getName());
        doctor.setSpecialization(details.getSpecialization());
        doctor.setContactNumber(details.getContactNumber());
        return doctorRepository.save(doctor);
    }
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        doctorRepository.delete(doctor);
    }


    public List<Doctor> searchBySpecialization(String spec) {
        return doctorRepository.findAll().stream()
                .filter(d -> d.getSpecialization().equalsIgnoreCase(spec))
                .collect(Collectors.toList());
    }
}
