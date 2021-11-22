/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.Patient;
import com.hospital.management.service.PatientService;
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
public class PatientController {

    private final PatientService patientService;
    

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patient = patientService.findAllPatients();
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/patient/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR', 'NURSE')")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        Patient patient = patientService.findPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/patient")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
        Patient newPatient = patientService.addPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @PutMapping("/patient/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Patient> updatePatient(@Valid @PathVariable("id") Long id, @Valid @RequestBody Patient patient) {
        Patient updatePatient = patientService.updatePatient(id, patient);
        return new ResponseEntity<>(updatePatient, HttpStatus.OK);
    }

    @DeleteMapping("/patient/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
