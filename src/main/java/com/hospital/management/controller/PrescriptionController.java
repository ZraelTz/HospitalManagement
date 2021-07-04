/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.dto.PrescriptionDto;
import com.hospital.management.service.PrescriptionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@AllArgsConstructor
@Slf4j
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody PrescriptionDto prescriptionDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(prescriptionService.save(prescriptionDto));
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionDto>> getAllPrescriptions() {
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
