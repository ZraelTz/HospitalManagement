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
import com.hospital.management.model.PatientMedicalRecord;
import com.hospital.management.repository.PatientMedicalRecordRepository;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PatientMedicalRecordService {

    private final PatientMedicalRecordRepository patientMedicalRecordRepository;
    private final PatientService patientService;
    private final AuthService authService;

    @Transactional
    public PatientMedicalRecord save(PatientMedicalRecord patientMedicalRecord) {

        patientMedicalRecord.setPatient(patientService.findPatientByUserEmail(authService.getCurrentUser().getUsername()));
        patientMedicalRecord.setCreatedDate(LocalDateTime.now());
        return patientMedicalRecordRepository.save(patientMedicalRecord);
    }

    @Transactional(readOnly = true)
    public List<PatientMedicalRecord> getAll() {
        if (authService.hasCurrentUserAnyOfThisAuthorities("DOCTOR", "NURSE")) {
            return patientMedicalRecordRepository.findAll();
        }
            return patientMedicalRecordRepository.findByPatientUserEmail(authService.getCurrentUser().getUsername());

    }

    @Transactional
    public PatientMedicalRecord updatePatientMedicalRecord(Long id, PatientMedicalRecord patientMedicalRecord) {
        if (!patientMedicalRecordRepository.existsById(id)) {
            throw new ManagementException("Medical Record with the id " + id + "was not found");
        }
        return save(patientMedicalRecord);
    }

    public void deletePatientMedicalRecord(Long id) {
        patientMedicalRecordRepository.deleteById(id);
    }

}
