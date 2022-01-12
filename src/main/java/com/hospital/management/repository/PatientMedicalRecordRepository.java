package com.hospital.management.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hospital.management.model.PatientMedicalRecord;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Zrael
 */


@Repository
public interface PatientMedicalRecordRepository extends JpaRepository<PatientMedicalRecord, Long> {

    List<PatientMedicalRecord> findByPatientUserEmail(String patientEmail);
}

    
