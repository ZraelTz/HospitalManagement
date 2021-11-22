/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.Doctor;
import com.hospital.management.service.DoctorService;
import com.hospital.management.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 *
 * @author Zrael
 */
@RestController
@RequestMapping("/api")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctor = doctorService.findAllDoctors();
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @GetMapping("/doctor/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("id") Long id) {
        Doctor doctor = doctorService.findDoctorById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PostMapping("/doctor")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
        Doctor newDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/doctor/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Doctor> updateDoctor(@Valid @PathVariable("id") Long id, @Valid @RequestBody Doctor doctor) {
        Doctor updateDoctor = doctorService.updateDoctor(id, doctor);
        return new ResponseEntity<>(updateDoctor, HttpStatus.OK);
    }

    @DeleteMapping("/doctor/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteDoctor(@PathVariable("id") Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
