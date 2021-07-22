/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.model;

import com.hospital.management.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zrael
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Service
public class Prescription {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotBlank(message = "Prescription title is required")
    private String prescriptionTitle;
    @NotBlank(message = "prescription is required")
    private String prescription;
    @NotBlank(message = "patient's full name is required is required")
    String patientFullName;
    @NotBlank(message = "patient's DOB is required is required")
    String patientDOB;
    String patientAddress;
    private Instant createdDate;
    @ManyToOne(fetch = LAZY)
    private Patient user;
}
