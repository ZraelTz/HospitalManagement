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
import com.hospital.management.model.Diagnosis;
import com.hospital.management.repository.DiagnosisRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AuthService authService;

    @Transactional
    public Diagnosis save(Diagnosis diagnosis) {

        diagnosis.setPatient(patientService.findPatientByUserEmail(diagnosis.getPatientEmail()));
        diagnosis.setDoctor(doctorService.findDoctorByUserEmail(authService.getCurrentUser().getUsername()));
        diagnosis.setCreatedDate(LocalDateTime.now());
        return diagnosisRepository.save(diagnosis);
    }

    @Transactional(readOnly = true)
    public List<Diagnosis> getAll() {
        if (authService.hasCurrentUserAnyOfThisAuthorities("DOCTOR")) {
            return diagnosisRepository.findByDoctorUserEmail(authService.getCurrentUser().getUsername());
        } else {
            return diagnosisRepository.findByPatientUserEmail(authService.getCurrentUser().getUsername());
        }

    }

    @Transactional
    public Diagnosis updateDiagnosis(Long id, Diagnosis diagnosis) {
        if (!diagnosisRepository.existsById(id)) {
            throw new ManagementException("Diagnosis with the id " + id + "was not found");
        }
        return save(diagnosis);
    }

    public void deleteDiagnosis(Long id) {
        diagnosisRepository.deleteById(id);
    }

}
