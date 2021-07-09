/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zrael
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionDto {
    
    Long id;
    String prescription;
    String prescriptionTitle;
    String patientFullName;
    String patientDOB;
    String patientAddress;
}
