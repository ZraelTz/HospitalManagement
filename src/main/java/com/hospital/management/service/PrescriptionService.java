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

import com.hospital.management.dto.PrescriptionDto;
import com.hospital.management.exceptions.ManagementException;
import com.hospital.management.model.Prescription;
import com.hospital.management.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
//    private final PrescriptionMapper prescriptionMapper;

    @Transactional
    public PrescriptionDto save(PrescriptionDto prescriptionDto) {
        Prescription p = prescriptionRepository
                .save(mapPrescriptionDto(prescriptionDto));
        prescriptionDto.setId(p.getId());
        return prescriptionDto;
    }

    @Transactional(readOnly = true)
    public List<PrescriptionDto> getAll() {
        return prescriptionRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }
    
    private PrescriptionDto mapToDto(Prescription prescription){
        return PrescriptionDto.builder()
                .patientFullName(prescription.getPatientFullName())
                .id(prescription.getId())
                .prescriptionTitle(prescription.getPrescriptionTitle())
                .build();
    }

//    public PrescriptionDto getPrescription(String prescriptionTitle) {
//        Prescription prescription = prescriptionRepository.findByPrescriptionTitle(prescriptionTitle)
//                .orElseThrow(() -> new ManagementException("No prescription found with the title - " + prescriptionTitle));
//        return prescriptionMapper.mapPrescriptionToDto(prescription);
//    }

    private Prescription mapPrescriptionDto(PrescriptionDto prescriptionDto) {
        return Prescription.builder()
                .patientFullName(prescriptionDto.getPatientFullName())
                .prescription(prescriptionDto.getPrescription())
                .prescriptionTitle(prescriptionDto.getPrescriptionTitle())
                .patientDOB(prescriptionDto.getPatientDOB())
                .patientAddress(prescriptionDto.getPatientAddress())
                .build();
    }
}
