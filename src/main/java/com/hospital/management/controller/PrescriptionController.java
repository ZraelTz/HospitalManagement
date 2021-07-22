/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.dto.PrescriptionDto;
import com.hospital.management.model.AppUserRole;
import com.hospital.management.service.AuthService;
import com.hospital.management.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *
 * @author Zrael
 */
@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final AuthService authService;
    
    public PrescriptionController(PrescriptionService prescriptionService, AuthService authService) {
        this.prescriptionService = prescriptionService;
        this.authService = authService;
    }

    @PostMapping("/create")
    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        AppUserRole userRole = authService.getRole();
        if (!userRole.equals(AppUserRole.DOCTOR)&&!userRole.equals(AppUserRole.NURSE)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prescriptionService.save(prescriptionDto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PrescriptionDto>> getAllPrescriptions() {
        AppUserRole userRole = authService.getRole();
        if (!userRole.equals(AppUserRole.ADMIN)&&
                !userRole.equals(AppUserRole.DOCTOR)&&
                !userRole.equals(AppUserRole.NURSE)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(prescriptionService.getAll());
    }

//    @GetMapping("/{title}")
//    public ResponseEntity<PrescriptionDto> getPrescription(@PathVariable String title) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(prescriptionService.getPrescription(title));
//    }
}
