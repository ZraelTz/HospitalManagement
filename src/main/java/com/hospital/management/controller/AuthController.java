/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.dto.AuthenticationResponse;
import com.hospital.management.dto.DoctorRegistrationRequest;
import com.hospital.management.dto.LoginRequest;
import com.hospital.management.dto.NurseRegistrationRequest;
import com.hospital.management.service.AuthService;
import com.hospital.management.dto.PatientRegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Zrael
 */

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/patient")
    public ResponseEntity<String> registerPatient(@RequestBody PatientRegistrationRequest request) {
        return authService.registerPatient(request);
    }
    
    @PostMapping("/doctor")
    public ResponseEntity<String> registerDoctor(@RequestBody DoctorRegistrationRequest request) {
        return authService.registerDoctor(request);
    }
    
    @PostMapping("/nurse")
    public ResponseEntity<String> registerNurse(@RequestBody NurseRegistrationRequest request) {
        return authService.registerNurse(request);
    }
    
    @GetMapping(path = "confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return authService.confirmToken(token);
    }
    
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest){
         return authService.login(loginRequest);
    }
}
