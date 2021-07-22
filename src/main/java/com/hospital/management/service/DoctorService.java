/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.LoginResponse;
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.Doctor;
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
import com.hospital.management.repository.DoctorRepository;

/**
 *
 * @author Zrael
 */
@Service("doctor")
@AllArgsConstructor

public class DoctorService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG
            = "user with email %s not found";
    private final DoctorRepository doctorRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Doctor> patientOptional
                = doctorRepository.findByEmail(email);
        Doctor doctor = patientOptional
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

        return new org.springframework.security.core.userdetails.User(doctor.getUsername(),
                doctor.getPassword(), doctor.isEnabled(), true,
                true, true, doctor.getAuthorities());
    }

    public LoginResponse findDoctorDetails(String email) throws UsernameNotFoundException {
        Optional<Doctor> appUserOptional
                = doctorRepository.findByEmail(email);
        Doctor doctor = appUserOptional
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return new LoginResponse(doctor.getUsername(), doctor.getUserRole(),
                doctor.getApprovedStatus(), doctor.getFirstName(), doctor.getLastName(),
                doctor.getOtherNames(), doctor.getPhone());
    }

    public AppUserRole findUserRole(String email) throws UsernameNotFoundException {
        Optional<Doctor> appUserOptional
                = doctorRepository.findByEmail(email);
        Doctor doctor = appUserOptional
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return doctor.getUserRole();
    }

    public String signUpDoctor(Doctor doctor) {
        boolean userExists = doctorRepository
                .findByEmail(doctor.getEmail())
                .isPresent();

        if (userExists) {
            //TODO: if email is not confirmed send confirmation email.
            throw new IllegalStateException("email taken");
        }

        String encodedPassword
                = bcryptPasswordEncoder.encode(doctor.getPassword());

        doctor.setPassword(encodedPassword);

        doctorRepository.save(doctor);

        //TODO: send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                doctor
        );

        confirmationTokenService.
                saveConfirmationToken(confirmationToken);

        //TODO: SEND EMAIL
        return token;
    }

    public int enableDoctor(String emailAddress) {
        return doctorRepository.enableDoctor(emailAddress);
    }

    public Doctor addDoctor(Doctor doctor) {

        return doctorRepository.save(doctor);
    }

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor findDoctorById(Long id) {
        return doctorRepository.findDoctorById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteDoctorById(id);
    }
}
