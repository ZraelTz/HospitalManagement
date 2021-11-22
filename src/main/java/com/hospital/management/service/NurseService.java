/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.NurseRegistrationRequest;
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.Nurse;
import com.hospital.management.model.User;
import com.hospital.management.model.UserRole;
import com.hospital.management.repository.NurseRepository;
import com.hospital.management.repository.UserRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
/**
 *
 * @author Zrael
 */

@Service("nurseService")
@AllArgsConstructor

public class NurseService {
    
    private final NurseRepository nurseRepository;
    private final UserRepository userRepository;
    private final UserService userService;
       
    public String signUpNurse(NurseRegistrationRequest newNurse){
        boolean userExists = userRepository
                .findByEmail(newNurse.getEmail())
                .isPresent();
        
        if(userExists) {
            //TODO: if email is not confirmed send confirmation email.
            throw new IllegalStateException("email taken");
        }
        User user = new User(newNurse.getEmail(), newNurse.getPassword(), UserRole.NURSE);
        Nurse nurse = new Nurse(
                newNurse.getFirstName(),
                newNurse.getLastName(),
                newNurse.getOtherNames(),
                newNurse.getAddress(),
                newNurse.getCountry(),
                newNurse.getCity(),
                newNurse.getState(),
                newNurse.getDob(),
                newNurse.getGender(),
                newNurse.getDepartment(),
                newNurse.getGovernmentId(),
                newNurse.getPhone()
        );
        
        nurse.setUser(user);
        String token = userService.signUpUser(user);
        nurseRepository.save(nurse);
        return token;
    }

    public Nurse addNurse(Nurse nurse) {

        return nurseRepository.save(nurse);
    }

    public List<Nurse> findAllNurses() {
        return nurseRepository.findAll();
    }

    public Nurse updateNurse(Long nurseId, Nurse nurse) {
        if(!nurseRepository.existsById(nurseId)) {
            throw new UserNotFoundException("Nurse with the id " + nurseId + "was not found");
        }
        return nurseRepository.save(nurse);
    }

    public Nurse findNurseById(Long id) {
        return nurseRepository.findNurseById(id)
                .orElseThrow(() -> new UserNotFoundException("Nurse with the id " + id + " was not found"));
    }
    
    public Nurse findNurseByUserEmail(String email) {
        return nurseRepository.findByUserEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Nurse with the email " + email + " was not found"));
    }

    public void deleteNurse(Long id) {
        nurseRepository.deleteNurseById(id);
    }
}
