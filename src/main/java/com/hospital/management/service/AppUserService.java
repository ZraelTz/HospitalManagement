/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.LoginResponse;
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.AppUser;
import com.hospital.management.model.AppUserRole;
import com.hospital.management.repository.AppUserRepository;
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
/**
 *
 * @author Zrael
 */

@Service
@AllArgsConstructor

public class AppUserService implements UserDetailsService{
    
    private final static String USER_NOT_FOUND_MSG = 
            "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> appUserOptional 
                = appUserRepository.findByEmail(email);
        AppUser appUser = appUserOptional
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        
        return new org.springframework.security
                .core.userdetails.User(appUser.getUsername(), 
                        appUser.getPassword(), appUser.isEnabled(), true, 
                        true, true, appUser.getAuthorities());
    }
    
    public LoginResponse findUserDetails(String email) throws UsernameNotFoundException{
        Optional<AppUser> appUserOptional
                = appUserRepository.findByEmail(email);
        AppUser appUser = appUserOptional
                 .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return new LoginResponse(appUser.getUsername(), appUser.getUserRole(), 
                appUser.getApprovedStatus(), appUser.getUserId());
    }
    
    public AppUserRole findUserRole(String email)  throws UsernameNotFoundException{
        Optional<AppUser> appUserOptional
                = appUserRepository.findByEmail(email);
        AppUser appUser = appUserOptional
                 .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return appUser.getUserRole();
    }
    
    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();
        
        if(userExists) {
            //TODO: if email is not confirmed send confirmation email.
            throw new IllegalStateException("email taken");
        }
        
        String encodedPassword = 
                bcryptPasswordEncoder.encode(appUser.getPassword());
        
        appUser.setPassword(encodedPassword);
        
        appUserRepository.save(appUser);
        
        //TODO: send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser        
        );
     
        confirmationTokenService.
                saveConfirmationToken(confirmationToken);
        
        //TODO: SEND EMAIL
        return token;
    }
    
        public int enableAppUser(String emailAddress) {
        return appUserRepository.enableAppUser(emailAddress);
    }

    public AppUser addAppUser(AppUser appUser) {

        return appUserRepository.save(appUser);
    }

    public List<AppUser> findAllAppUsers() {
        return appUserRepository.findAll();
    }

    public AppUser updateAppUser(AppUser appUser) {
        return appUserRepository.save(appUser);
    }

    public AppUser findAppUserById(Long id) {
        return appUserRepository.findAppUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteAppUser(Long id) {
        appUserRepository.deleteAppUserById(id);
    }
}
