/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Zrael
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DoctorRegistrationRequest {

    @NotBlank(message = "your email is required")
    private final String email;
    
    @NotBlank(message = "your password is required")
    private final String password;
    
    @NotBlank(message = "your firstname is required")
    private final String firstName;
    
    @NotBlank(message = "your lastname is required")
    private final String lastName;
    
    @NotBlank(message = "your othernames are required")
    private final String otherNames;
    
    @NotBlank(message = "your address is required")
    private final String address;
    
    @NotBlank(message = "your country is required")
    private final String country;
    
    @NotBlank(message = "your city is required")
    private final String city;
    
    @NotBlank(message = "your state is required")
    private final String state;
    
    @NotBlank(message = "your date of birth is required")
    private final String dob;
    
    @NotBlank(message = "your gender is required")
    private final String gender;
    
    @NotBlank(message = "your department is required")
    private final String department;
    
    @NotBlank(message = "your specialization is required")
    private final String specialization;
    
    @NotBlank(message = "your governmentId is required")
    private final String governmentId;
    
    @NotBlank(message = "your phone is required")
    private final String phone;
    
    
}
