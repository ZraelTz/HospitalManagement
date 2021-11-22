/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class PatientRegistrationRequest {
    
    @NotNull
    @NotBlank(message = "your email is required")
    private final String email;
    
    @NotNull
    @NotBlank(message = "your password is required")
    private final String password;
    
    @NotNull
    @NotBlank(message = "your firstname is required")
    private final String firstName;
    
    @NotNull
    @NotBlank(message = "your lastname is required")
    private final String lastName;
    
    @NotNull
    @NotBlank(message = "your othernames are required")
    private final String otherNames;
    
    @NotNull
    @NotBlank(message = "your address is required")
    private final String address;
    
    @NotNull
    @NotBlank(message = "your country is required")
    private final String country;
    
    @NotNull
    @NotBlank(message = "your city is required")
    private final String city;
    
    @NotNull
    @NotBlank(message = "your state is required")
    private final String state;
    
    @NotNull
    @NotBlank(message = "your date of birth is required")
    private final String dob;
    
    @NotNull
    @NotBlank(message = "your gender is required")
    private final String gender;
    
    @NotNull
    @NotBlank(message = "your phone contact is required")
    private final String phone;
    
    @NotNull
    @NotBlank(message = "your weight is required")
    private final float weight;
    
    @NotNull
    @NotBlank(message = "your height is required")
    private final float height;
}
