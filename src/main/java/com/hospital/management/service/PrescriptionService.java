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
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.Prescription;
import com.hospital.management.model.UserRole;
import com.hospital.management.repository.PrescriptionRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
@AllArgsConstructor
@Slf4j
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final NurseService nurseService;
    private final AuthService authService;

    @Transactional
    public Prescription save(Prescription prescription) {

        if (authService.hasCurrentUserAnyOfThisAuthorities("DOCTOR")) {
            prescription.setDoctor(doctorService.findDoctorByUserEmail(authService.getCurrentUser().getUsername()));
        }
        
        if (authService.hasCurrentUserAnyOfThisAuthorities("NURSE")) {
            prescription.setNurse(nurseService.findNurseByUserEmail(authService.getCurrentUser().getUsername()));
        }
        prescription.setPatient(patientService.findPatientByUserEmail(prescription.getPatientEmail()));

        prescription.setCreatedDate(LocalDateTime.now());
        return prescriptionRepository.save(prescription);
    }

    @Transactional(readOnly = true)
    public List<Prescription> getAll() {
        if (authService.hasCurrentUserAnyOfThisAuthorities("DOCTOR")) {
            return prescriptionRepository.findByDoctorUserEmail(authService.getCurrentUser().getUsername());
        } else if (authService.hasCurrentUserAnyOfThisAuthorities("NURSE")) {
            return prescriptionRepository.findByNurseUserEmail(authService.getCurrentUser().getUsername());
        } else {
            return prescriptionRepository.findByPatientUserEmail(authService.getCurrentUser().getUsername());
        }

    }

    @Transactional
    public Prescription updatePrescription(Long prescriptionId, Prescription prescription) {
        if (!prescriptionRepository.existsById(prescriptionId)) {
            throw new UserNotFoundException("Prescription with the id " + prescriptionId + "was not found");
        }
        return save(prescription);
    }

    public void deletePrescription(Long id) {
        prescriptionRepository.deleteById(id);
    }

}
