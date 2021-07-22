/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.dto;

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
    
    private final String firstName;
    private final String lastName;
    private final String otherNames;
    private final String address;
    private final String country;
    private final String city;
    private final String countryState;
    private final String email;
    private final String password;
    private final String dob;
    private final String gender;
    private final String phone;
}
