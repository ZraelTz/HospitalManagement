/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.Patient;
import com.hospital.management.model.AppUserRole;
import com.hospital.management.service.PatientService;
import com.hospital.management.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Zrael
 */
@RestController
@RequestMapping("api/patient")
public class PatientController {

    private final AuthService authService;
    private final PatientService patientService;
    

    public PatientController(PatientService patientService, AuthService authService) {
        this.patientService = patientService;
        this.authService = authService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        if(!authService.getRole().equals(AppUserRole.ADMIN)){
            List<Patient> appUsers = null;
            return new ResponseEntity<>(appUsers, HttpStatus.FORBIDDEN);
        }
        List<Patient> appUser = patientService.findAllPatients();
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) {
        if(!authService.getRole().equals(AppUserRole.ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        Patient appUser = patientService.findPatientById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient appUser) {
        if (!authService.getRole().equals(AppUserRole.ADMIN)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        Patient newAppUser = patientService.addPatient(appUser);
        return new ResponseEntity<>(newAppUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient appUser) {
        if (!authService.getRole().equals(AppUserRole.ADMIN)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        Patient updateAppUser = patientService.updatePatient(appUser);
        return new ResponseEntity<>(updateAppUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable("id") Long id) {
        if (!authService.getRole().equals(AppUserRole.ADMIN)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
