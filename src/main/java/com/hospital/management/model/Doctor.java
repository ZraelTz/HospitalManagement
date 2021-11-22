/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Zrael
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Doctor implements Serializable {

    //Database table sequence generators
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )

    //Doctor Details
    private Long id;
    
    @OneToOne
    @JoinColumn(
            nullable = true,
            name = "user_id"
    )
    private User user;

    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @NotBlank(message = "your middle name is required")
    private String otherNames;

    @NotBlank(message = "your address is required")
    private String address;

    @NotBlank(message = "country is required")
    private String country;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "your birth state is required")
    @Column(name="birth_state")
    private String state;
    
    @NotBlank(message = "your date of birth is required")
    private String dob;

    @NotBlank(message = "required")
    private String gender;
    
    @NotBlank(message = "your department is required")
    private String department;
    
    @NotBlank(message = "your specialization is required")
    private String specialization;
    
    @NotBlank(message = "your government id is required")
    private String governmentId;

    @NotBlank(message = "your phone number is required")
    private String phone;

    //Class constructor
    public Doctor(String firstName, String lastName, String otherNames, String address, 
            String country, String city, String state, String dob, String gender, 
            String department, String specialization, String governmentId, String phone) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherNames = otherNames;
        this.address = address;
        this.country = country;
        this.city = city;
        this.state = state;
        this.dob = dob;
        this.gender = gender;
        this.department = department;
        this.specialization = specialization;
        this.governmentId = governmentId;
        this.phone = phone;
    }

}
