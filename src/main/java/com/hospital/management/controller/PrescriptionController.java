/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.Prescription;
import com.hospital.management.service.AuthService;
import com.hospital.management.service.PrescriptionService;
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
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    
    public PrescriptionController(PrescriptionService prescriptionService, AuthService authService) {
        this.prescriptionService = prescriptionService;
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'NURSE')")
    @PostMapping("/prescription")
    public ResponseEntity<Prescription> createPrescription(@Valid @RequestBody Prescription prescription) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prescriptionService.save(prescription));
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'PATIENT', 'NURSE')")
    @GetMapping("/prescription")
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(prescriptionService.getAll());
    }

    
    @PreAuthorize("hasAnyAuthority('DOCTOR', 'NURSE')")
    @PutMapping("/prescription/{id}")
    public ResponseEntity<Prescription> updatePrescription(@Valid @PathVariable("id") Long id, @Valid @RequestBody Prescription prescription) {
        Prescription updatePrescription = prescriptionService.updatePrescription(id, prescription);
        return new ResponseEntity<>(updatePrescription, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'NURSE')")
    @DeleteMapping("/prescription/{id}")
    public ResponseEntity<?> deletePrescription(@PathVariable("id") Long id) {
        prescriptionService.deletePrescription(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
