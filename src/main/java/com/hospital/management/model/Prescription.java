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
import java.time.LocalDateTime;

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
public class Prescription implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    
    @NotBlank(message = "Prescription title is required")
    private String prescriptionTitle;
    
    @NotBlank(message = "Prescription is required")
    private String prescription;
    
    @NotBlank(message = "Patient email is required")
    private String patientEmail;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = LAZY)
    private Patient patient;
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = LAZY)
    private Doctor doctor;   
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = LAZY)
    private Nurse nurse;
    
    private LocalDateTime createdDate;
}
