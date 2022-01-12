/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Zrael
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PatientMedicalRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotBlank(message = "please provide your disability info, if you have non, input non")
    private String disablities;
    
    @NotBlank(message = " your blood type is required")
    private BloodType bloodType;
    
    @NotBlank(message = "your blood genotype is required")
    private BloodGenotype bloodGenotype;
    
    @NotBlank(message = "please provide your allergies info, if you have non, input non")
    private String allergies;
    
    @NotBlank(message = "please provide your diagnosed medical problems if you have any, if you have non, input non")
    private String diagnosedMedicalProblems;
    
    @NotBlank(message = "please provide your current medicatations if you have any, if you have non, input non")
    private String currentMedications;
    
    @NotBlank(message = "please provide info about any surgeries that you have had in the past or recently, if you have non, input non")
    private String surgicalHistory;
    
    @NotNull(message = "please input true or false, if you have any medical insurance or not")
    private Boolean hasMedicalInsurance;
    
    private String insuranceCompanyName;
    
    private Long insurancePolicyNumber;
    
    private String insuranceExpiryDate;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToOne(fetch = LAZY)
    private Patient patient;
    
    private LocalDateTime createdDate;
}
