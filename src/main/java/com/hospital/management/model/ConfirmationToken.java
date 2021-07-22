/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Zrael
 */

@Getter
@Setter
@NoArgsConstructor
@Entity

public class ConfirmationToken {
    
        @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )  
    
    private Long id;
        
    @Column(nullable = false)
    private String token;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    
    private LocalDateTime confirmedAt;
    
    @ManyToOne
    @JoinColumn(
            nullable = true,
            name = "patient_id"
    )
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(
            nullable = true,
            name = "doctor_id"
    )
    private Doctor doctor;
    
    @ManyToOne
    @JoinColumn(
            nullable = true,
            name = "nurse_id"
    )
    private Nurse nurse;
    
    public ConfirmationToken(String token, 
            LocalDateTime createdAt, 
            LocalDateTime expiresAt, 
            Patient patient) {
        
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.patient = patient;
    }
    
    public ConfirmationToken(String token,
            LocalDateTime createdAt,
            LocalDateTime expiresAt,
            Doctor doctor) {

        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.doctor = doctor;
    }
    
    public ConfirmationToken(String token,
            LocalDateTime createdAt,
            LocalDateTime expiresAt,
            Nurse nurse) {

        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.nurse = nurse;
    }
    

    
    
}
