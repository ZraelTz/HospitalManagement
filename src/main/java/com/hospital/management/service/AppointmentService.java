/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

/**
 *
 * @author Zrael
 */
import com.hospital.management.exceptions.ManagementException;
import com.hospital.management.model.Appointment;
import com.hospital.management.model.AppointmentStatus;
import com.hospital.management.repository.AppointmentRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
@AllArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final NurseService nurseService;
    private final AuthService authService;

    @Transactional
    public Appointment save(Appointment appointment) {

        if (authService.hasCurrentUserAnyOfThisAuthorities("DOCTOR")) {
            appointment.setDoctor(doctorService.findDoctorByUserEmail(authService.getCurrentUser().getUsername()));
        }

        if (authService.hasCurrentUserAnyOfThisAuthorities("NURSE")) {
            appointment.setNurse(nurseService.findNurseByUserEmail(authService.getCurrentUser().getUsername()));
        }
        appointment.setPatient(patientService.findPatientByUserEmail(appointment.getPatientEmail()));
        appointment.setStatus(AppointmentStatus.PENDING);

        appointment.setCreatedDate(LocalDateTime.now());
        appointment.setUnfulfillsAt(appointment.getAppointmentDate().plusHours(24));
        return appointmentRepository.save(appointment);
    }

    @Transactional(readOnly = true)
    public List<Appointment> getAll() {
        if (authService.hasCurrentUserAnyOfThisAuthorities("DOCTOR")) {
            return appointmentRepository.findByDoctorUserEmail(authService.getCurrentUser().getUsername());
        } else if (authService.hasCurrentUserAnyOfThisAuthorities("NURSE")) {
            return appointmentRepository.findByNurseUserEmail(authService.getCurrentUser().getUsername());
        } else {
            return appointmentRepository.findByPatientUserEmail(authService.getCurrentUser().getUsername());
        }
    }

    @Transactional
    public Appointment updateAppointment(Long id, Appointment appointment) {
        if (!appointmentRepository.existsById(id)) {
            throw new ManagementException("Appointment with the id " + id + "was not found");
        }
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public ResponseEntity<String> fulfillAppointment(Long appointmentId, AppointmentStatus appointmentStatus) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();

        if (appointment.getUnfulfillsAt().isBefore(LocalDateTime.now())) {
            appointmentRepository.fulfilAppointment(appointmentId, AppointmentStatus.UNFULFILLED);
            throw new IllegalStateException("Sorry You have missed this Appointment, The Appointment Date has passed");
        } else if (appointment.getStatus().equals(AppointmentStatus.CANCELLED)) {
            return new ResponseEntity<>("Your appointment for " + appointment.getAppointmentDate()
                    + " has already been cancelled",
                    HttpStatus.GONE);
        } else {
            appointmentRepository.fulfilAppointment(appointmentId, appointmentStatus);
        }

        return new ResponseEntity<>("You have completed your appointment for " + appointment.getAppointmentDate(),
                HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> unfulfillAppointment(Long appointmentId, AppointmentStatus appointmentStatus) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();

        if (appointment.getUnfulfillsAt().isBefore(LocalDateTime.now())) {
            appointmentRepository.fulfilAppointment(appointmentId, appointmentStatus);
            return new ResponseEntity<>("You missed your appointment for " + appointment.getAppointmentDate(),
                    HttpStatus.GONE);
        } else if (appointment.getStatus().equals(AppointmentStatus.CANCELLED)) {
            return new ResponseEntity<>("Your appointment for " + appointment.getAppointmentDate()
                    + " has already been cancelled",
                    HttpStatus.GONE);
        } else {
            throw new IllegalStateException("Sorry this Appointment is still pending and can be fulfilled");
        }
    }

    @Transactional
    public ResponseEntity<String> cancelAppointment(Long appointmentId, AppointmentStatus appointmentStatus) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();
        appointmentRepository.cancelAppointment(appointmentId, appointmentStatus);
        return new ResponseEntity<>("Your appointment for " + appointment.getAppointmentDate() + " is cancelled",
                HttpStatus.OK);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

}
