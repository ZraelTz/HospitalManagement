/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.LoginResponse;
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.Patient;
import com.hospital.management.model.AppUserRole;
import com.hospital.management.model.ConfirmationToken;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.hospital.management.repository.PatientRepository;
import org.springframework.context.annotation.Primary;
/**
 *
 * @author Zrael
 */

@Service("patient")
@Primary
@AllArgsConstructor

public class PatientService implements UserDetailsService{
    
    private final static String USER_NOT_FOUND_MSG = 
            "user with email %s not found";
    private final PatientRepository patientRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Patient> patientOptional 
                = patientRepository.findByEmail(email);
        Patient patient = patientOptional
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        
        return new org.springframework.security
                .core.userdetails.User(patient.getUsername(), 
                        patient.getPassword(), patient.isEnabled(), true, 
                        true, true, patient.getAuthorities());
    }
    
    public LoginResponse findPatientDetails(String email) throws UsernameNotFoundException{
        Optional<Patient> appUserOptional
                = patientRepository.findByEmail(email);
        Patient patient = appUserOptional
                 .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return new LoginResponse(patient.getUsername(), patient.getUserRole(), 
                patient.getApprovedStatus(), patient.getFirstName(), patient.getLastName(),
                patient.getOtherNames(), patient.getPhone());
    }
    
    public AppUserRole findUserRole(String email)  throws UsernameNotFoundException{
        Optional<Patient> appUserOptional
                = patientRepository.findByEmail(email);
        Patient patient = appUserOptional
                 .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return patient.getUserRole();
    }
    
    public String signUpPatient(Patient patient){
        boolean userExists = patientRepository
                .findByEmail(patient.getEmail())
                .isPresent();
        
        if(userExists) {
            //TODO: if email is not confirmed send confirmation email.
            throw new IllegalStateException("email taken");
        }
        
        String encodedPassword = 
                bcryptPasswordEncoder.encode(patient.getPassword());
        
        patient.setPassword(encodedPassword);
        
        patientRepository.save(patient);
        
        //TODO: send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                patient        
        );
     
        confirmationTokenService.
                saveConfirmationToken(confirmationToken);
        
        //TODO: SEND EMAIL
        return token;
    }
    
        public int enablePatient(String emailAddress) {
        return patientRepository.enablePatient(emailAddress);
    }

    public Patient addPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findPatientById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deletePatient(Long id) {
        patientRepository.deletePatientById(id);
    }
}
