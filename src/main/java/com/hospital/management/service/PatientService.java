/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.PatientRegistrationRequest;
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.Patient;
import com.hospital.management.model.User;
import com.hospital.management.model.UserRole;
import com.hospital.management.repository.PatientRepository;
import com.hospital.management.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
/**
 *
 * @author Zrael
 */

@Service("patientService")
@AllArgsConstructor

public class PatientService {
    
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final UserService userService;
       
    public String signUpPatient(PatientRegistrationRequest newPatient){
        boolean userExists = userRepository
                .findByEmail(newPatient.getEmail())
                .isPresent();
        
        if(userExists) {
            //TODO: if email is not confirmed send confirmation email.
            throw new IllegalStateException("email taken");
        }
        
        User user = new User(newPatient.getEmail(), newPatient.getPassword(), UserRole.PATIENT);
        
        Patient patient = new Patient(
                newPatient.getFirstName(), 
                newPatient.getLastName(), 
                newPatient.getOtherNames(), 
                newPatient.getAddress(),
                newPatient.getCountry(), 
                newPatient.getCity(), 
                newPatient.getState(), 
                newPatient.getDob(), 
                newPatient.getGender(), 
                newPatient.getPhone(),
                newPatient.getWeight(),
                newPatient.getHeight()
        );
        
        patient.setUser(user);
        String token = userService.signUpUser(user);
        patientRepository.save(patient);
        return token;
    }
    

    public Patient addPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Patient updatePatient(Long patientId, Patient patient) {
        if (!patientRepository.existsById(patientId)) {
            throw new UserNotFoundException("Patient with the id " + patientId + "was not found");
        }
        return patientRepository.save(patient);
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findPatientById(id)
                .orElseThrow(() -> new UserNotFoundException("Patient with the id " + id + " was not found"));
    }
    
    public Patient findPatientByUserEmail(String email) {
        return patientRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Patient with the email " + email + " was not found"));
    }

    public void deletePatient(Long id) {
        patientRepository.deletePatientById(id);
    }
}
