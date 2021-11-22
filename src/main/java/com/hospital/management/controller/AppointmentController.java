/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.Appointment;
import com.hospital.management.model.AppointmentStatus;
import com.hospital.management.service.AuthService;
import com.hospital.management.service.AppointmentService;
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
public class AppointmentController {

    private final AppointmentService appointmentService;
    
    public AppointmentController(AppointmentService appointmentService, AuthService authService) {
        this.appointmentService = appointmentService;
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'NURSE')")
    @PostMapping("/appointment")
    public ResponseEntity<Appointment> createAppointment(@Valid @RequestBody Appointment appointment) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.save(appointment));
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'PATIENT', 'NURSE')")
    @GetMapping("/appointment")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(appointmentService.getAll());
    }
    
    @PreAuthorize("hasAnyAuthority('DOCTOR', 'PATIENT', 'NURSE')")
    @GetMapping("/appointment/fulfil/{id}")
    public ResponseEntity<String> fulfilAppointment(@PathVariable("id") Long id) {
        return appointmentService.fulfillAppointment(id, AppointmentStatus.FULFILLED);
    }
    
    @PreAuthorize("hasAnyAuthority('DOCTOR', 'PATIENT', 'NURSE')")
    @GetMapping("/appointment/unfulfil/{id}")
    public ResponseEntity<String> unfulfilAppointment(@PathVariable("id") Long id) {
        return appointmentService.unfulfillAppointment(id, AppointmentStatus.UNFULFILLED);
    }
    
    @PreAuthorize("hasAnyAuthority('DOCTOR', 'PATIENT', 'NURSE')")
    @GetMapping("/appointment/cancel/{id}")
    public ResponseEntity<String> cancelAppointment(@PathVariable("id") Long id) {
        return appointmentService.cancelAppointment(id, AppointmentStatus.CANCELLED);
    }
    
    @PreAuthorize("hasAnyAuthority('DOCTOR', 'NURSE')")
    @PutMapping("/appointment/{id}")
    public ResponseEntity<Appointment> updateAppointment(@Valid @PathVariable("id") Long id, @Valid @RequestBody Appointment appointment) {
        Appointment updateAppointment = appointmentService.updateAppointment(id, appointment);
        return new ResponseEntity<>(updateAppointment, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('DOCTOR', 'NURSE')")
    @DeleteMapping("/appointment/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
