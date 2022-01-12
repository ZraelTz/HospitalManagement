/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.PatientMedicalRecord;
import com.hospital.management.service.AuthService;
import com.hospital.management.service.PatientMedicalRecordService;
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
public class PatientMedicalRecordController {

    private final PatientMedicalRecordService patientMedicalRecordService;
    
    public PatientMedicalRecordController(PatientMedicalRecordService patientMedicalRecordService, AuthService authService) {
        this.patientMedicalRecordService = patientMedicalRecordService;
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @PostMapping("/patient-medical-record")
    public ResponseEntity<PatientMedicalRecord> createPatientMedicalRecord(@Valid @RequestBody PatientMedicalRecord patientMedicalRecors) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientMedicalRecordService.save(patientMedicalRecors));
    }

    @PreAuthorize("hasAnyAuthority('PATIENT', 'DOCTOR', 'NURSE')")
    @GetMapping("/patient-medical-record")
    public ResponseEntity<List<PatientMedicalRecord>> getAllPatientMedicalRecords() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientMedicalRecordService.getAll());
    }

    
    @PreAuthorize("hasAnyAuthority('PATIENT')")
    @PutMapping("/patient-medical-record/{id}")
    public ResponseEntity<PatientMedicalRecord> updatePatientMedicalRecord(@Valid @PathVariable("id") Long id, @Valid @RequestBody PatientMedicalRecord patientMedicalRecors) {
        PatientMedicalRecord updatePatientMedicalRecord = patientMedicalRecordService.updatePatientMedicalRecord(id, patientMedicalRecors);
        return new ResponseEntity<>(updatePatientMedicalRecord, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('PATIENT', 'DOCTOR', 'NURSE')")
    @DeleteMapping("/patient-medical-record/{id}")
    public ResponseEntity<?> deletePatientMedicalRecord(@PathVariable("id") Long id) {
        patientMedicalRecordService.deletePatientMedicalRecord(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
