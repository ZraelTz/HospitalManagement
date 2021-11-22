/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.Diagnosis;
import com.hospital.management.service.AuthService;
import com.hospital.management.service.DiagnosisService;
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
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    
    public DiagnosisController(DiagnosisService diagnosisService, AuthService authService) {
        this.diagnosisService = diagnosisService;
    }

    @PreAuthorize("hasAuthority('DOCTOR')")
    @PostMapping("/diagnosis")
    public ResponseEntity<Diagnosis> createDiagnosis(@Valid @RequestBody Diagnosis diagnosis) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(diagnosisService.save(diagnosis));
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'PATIENT')")
    @GetMapping("/diagnosis")
    public ResponseEntity<List<Diagnosis>> getAllDiagnosis() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(diagnosisService.getAll());
    }

    
    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    @PutMapping("/diagnosis/{id}")
    public ResponseEntity<Diagnosis> updateDiagnosis(@Valid @PathVariable("id") Long id, @Valid @RequestBody Diagnosis diagnosis) {
        Diagnosis updateDiagnosis = diagnosisService.updateDiagnosis(id, diagnosis);
        return new ResponseEntity<>(updateDiagnosis, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR')")
    @DeleteMapping("/diagnosis/{id}")
    public ResponseEntity<?> deleteDiagnosis(@PathVariable("id") Long id) {
        diagnosisService.deleteDiagnosis(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
