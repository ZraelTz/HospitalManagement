/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author Zrael
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Diagnosis implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotBlank(message = "please provide the diagnosis severtity, if you have non, input non")
    private DiagnosisSeverity diagnosisSeverity;
    
    @NotBlank(message = " the diagnosis information is required")
    private String diagnosisInfo;
    
    @NotBlank(message = "the patient symptoms is required")
    private String symptoms;
    
    @NotBlank(message = "please provide info about your clinical findings info")
    private String clinicalFindings;
    
    @NotBlank(message = "please provide your remarks concerning this diagnosis, if you have non, input non")
    private String additionalRemarks;
    
    @NotBlank(message = "Patient email is required")
    private String patientEmail;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = LAZY)
    private Patient patient;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = LAZY)
    private Doctor doctor;
    
    private LocalDateTime createdDate;
}
