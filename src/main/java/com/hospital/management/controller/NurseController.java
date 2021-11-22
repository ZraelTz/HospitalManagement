/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.Nurse;
import com.hospital.management.service.NurseService;
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
public class NurseController {

    private final NurseService nurseService;
    

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping("/nurse")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR',)")
    public ResponseEntity<List<Nurse>> getAllNurses() {
        List<Nurse> nurse = nurseService.findAllNurses();
        return new ResponseEntity<>(nurse, HttpStatus.OK);
    }

    @GetMapping("/nurse/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'DOCTOR',)")
    public ResponseEntity<Nurse> getNurseById(@PathVariable("id") Long id) {
        Nurse nurse = nurseService.findNurseById(id);
        return new ResponseEntity<>(nurse, HttpStatus.OK);
    }

    @PostMapping("/nurse")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Nurse> addNurse(@Valid @RequestBody Nurse nurse) {
        Nurse newNurse = nurseService.addNurse(nurse);
        return new ResponseEntity<>(newNurse, HttpStatus.CREATED);
    }

    @PutMapping("/nurse/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Nurse> updateNurse(@Valid @PathVariable("id") Long id, @Valid @RequestBody Nurse nurse) {
        Nurse updateNurse = nurseService.updateNurse(id, nurse);
        return new ResponseEntity<>(updateNurse, HttpStatus.OK);
    }

    @DeleteMapping("/nurse/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteNurse(@PathVariable("id") Long id) {
        nurseService.deleteNurse(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
