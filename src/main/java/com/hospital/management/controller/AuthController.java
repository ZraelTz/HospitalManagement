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
import javax.validation.Valid;
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
@RequestMapping(path = "/api")
@AllArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping("/registration/patient")
    public ResponseEntity<String> registerPatient(@Valid @RequestBody PatientRegistrationRequest request) {
        return authService.registerPatient(request);
    }
    
    @PostMapping("/registration/doctor")
    public ResponseEntity<String> registerDoctor(@Valid @RequestBody DoctorRegistrationRequest request) {
        return authService.registerDoctor(request);
    }
    
    @PostMapping("/registration/nurse")
    public ResponseEntity<String> registerNurse(@Valid @RequestBody NurseRegistrationRequest request) {
        return authService.registerNurse(request);
    }
    
    @GetMapping("/registration/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return authService.confirmToken(token);
    }
    
    @GetMapping("/registration/resend-confirmation-email")
    public ResponseEntity<String> resend() {
        return authService.resendConfirmationEmail();
    }
    
    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest loginRequest){
         return authService.login(loginRequest);
    }
}
