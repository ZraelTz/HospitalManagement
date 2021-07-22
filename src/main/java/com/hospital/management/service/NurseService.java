/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.LoginResponse;
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.Nurse;
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
import com.hospital.management.repository.NurseRepository;

/**
 *
 * @author Zrael
 */

@Service("nurse")
@AllArgsConstructor

public class NurseService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG
            = "user with email %s not found";
    private final NurseRepository nurseRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Nurse> patientOptional
                = nurseRepository.findByEmail(email);
        Nurse nurse = patientOptional
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));

        return new org.springframework.security.core.userdetails.User(nurse.getUsername(),
                nurse.getPassword(), nurse.isEnabled(), true,
                true, true, nurse.getAuthorities());
    }

    public LoginResponse findNurseDetails(String email) throws UsernameNotFoundException {
        Optional<Nurse> appUserOptional
                = nurseRepository.findByEmail(email);
        Nurse nurse = appUserOptional
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return new LoginResponse(nurse.getUsername(), nurse.getUserRole(),
                nurse.getApprovedStatus(), nurse.getFirstName(), nurse.getLastName(),
                nurse.getOtherNames(), nurse.getPhone());
    }

    public AppUserRole findUserRole(String email) throws UsernameNotFoundException {
        Optional<Nurse> appUserOptional
                = nurseRepository.findByEmail(email);
        Nurse nurse = appUserOptional
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return nurse.getUserRole();
    }

    public String signUpNurse(Nurse nurse) {
        boolean userExists = nurseRepository
                .findByEmail(nurse.getEmail())
                .isPresent();

        if (userExists) {
            //TODO: if email is not confirmed send confirmation email.
            throw new IllegalStateException("email taken");
        }

        String encodedPassword
                = bcryptPasswordEncoder.encode(nurse.getPassword());

        nurse.setPassword(encodedPassword);

        nurseRepository.save(nurse);

        //TODO: send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                nurse
        );

        confirmationTokenService.
                saveConfirmationToken(confirmationToken);

        //TODO: SEND EMAIL
        return token;
    }

    public int enableNurse(String emailAddress) {
        return nurseRepository.enableNurse(emailAddress);
    }

    public Nurse addNurse(Nurse nurse) {

        return nurseRepository.save(nurse);
    }

    public List<Nurse> findAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse updateNurse(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public Nurse findNurseById(Long id) {
        return nurseRepository.findNurseById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteNurse(Long id) {
        nurseRepository.deleteNurseById(id);
    }
}
