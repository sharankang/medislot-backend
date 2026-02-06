package com.medislot.medislot_backend.controller;

import com.medislot.medislot_backend.entity.Doctor;
import com.medislot.medislot_backend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Tells Spring this class handles REST API requests
@RequestMapping("/api/doctors") //Base URL for all endpoints in this class
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    //POST endpoint- create a new doctor
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }

    //GET endpoint- fetch all doctors
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}