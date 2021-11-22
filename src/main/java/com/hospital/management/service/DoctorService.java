/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.DoctorRegistrationRequest;
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.Doctor;
import com.hospital.management.model.User;
import com.hospital.management.model.UserRole;
import com.hospital.management.repository.DoctorRepository;
import com.hospital.management.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
/**
 *
 * @author Zrael
 */

@Service("doctorService")
@AllArgsConstructor

public class DoctorService {
    
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final UserService userService;
       
    public String signUpDoctor(DoctorRegistrationRequest newDoctor){
        boolean userExists = userRepository
                .findByEmail(newDoctor.getEmail())
                .isPresent();
        
        if(userExists) {
            //TODO: if email is not confirmed send confirmation email.
            throw new IllegalStateException("email taken");
        }
        
        User user = new User(newDoctor.getEmail(), newDoctor.getPassword(), UserRole.DOCTOR);
        Doctor doctor = new Doctor(
                newDoctor.getFirstName(),
                newDoctor.getLastName(),
                newDoctor.getOtherNames(),
                newDoctor.getAddress(),
                newDoctor.getCountry(),
                newDoctor.getCity(),
                newDoctor.getState(),
                newDoctor.getDob(),
                newDoctor.getGender(),
                newDoctor.getDepartment(),
                newDoctor.getSpecialization(),
                newDoctor.getGovernmentId(),
                newDoctor.getPhone()
        );
        
        doctor.setUser(user);
        String token = userService.signUpUser(user);
        doctorRepository.save(doctor);        
        return token;
    }

    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(Long doctorId, Doctor doctor) {
        if (!doctorRepository.existsById(doctorId)) {
            throw new UserNotFoundException("Doctor with the id " + doctorId + "was not found");
        }
        return doctorRepository.save(doctor);
    }

    public Doctor findDoctorById(Long id) {
        return doctorRepository.findDoctorById(id)
                .orElseThrow(() -> new UserNotFoundException("Doctor with the id " + id + " was not found"));
    }
    
    public Doctor findDoctorByUserEmail(String email) {
        return doctorRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Doctor with the email " + email + " was not found"));
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteDoctorById(id);
    }
}
