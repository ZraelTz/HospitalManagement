/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.LoginResponse;
import com.hospital.management.exceptions.UserNotFoundException;
import com.hospital.management.model.User;
import com.hospital.management.model.UserRole;
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
import com.hospital.management.repository.UserRepository;
/**
 *
 * @author Zrael
 */

@Service("userService")
@AllArgsConstructor

public class UserService implements UserDetailsService{
    
    private final static String USER_NOT_FOUND_MSG = 
            "user with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional 
                = userRepository.findByEmail(email);
        User user = userOptional
                .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        
        return new org.springframework.security
                .core.userdetails.User(user.getUsername(), 
                        user.getPassword(), user.isEnabled(), true, 
                        true, true, user.getAuthorities());
    }
    
    public LoginResponse findUserDetails(String email) throws UsernameNotFoundException{
        Optional<User> appUserOptional
                = userRepository.findByEmail(email);
        User user = appUserOptional
                 .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return new LoginResponse(user.getUsername(), user.getUserRole(), 
                user.getApprovedStatus(), user.getEnabled(), user.getId());
    }
    
    public UserRole findUserRole(String email)  throws UsernameNotFoundException{
        Optional<User> appUserOptional
                = userRepository.findByEmail(email);
        User user = appUserOptional
                 .orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        return user.getUserRole();
    }
    
    public String signUpUser(User user){
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        
        if(userExists) {
            //TODO: if email is not confirmed send confirmation email.
            throw new IllegalStateException("email taken");
        }
        
        String encodedPassword = 
                bcryptPasswordEncoder.encode(user.getPassword());
        
        user.setPassword(encodedPassword);
        userRepository.save(user);
        
        //TODO: send confirmation token
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user        
        );
     
        confirmationTokenService.
                saveConfirmationToken(confirmationToken);
        
        //TODO: SEND EMAIL
        return token;
    }
    
        public int enableUser(String emailAddress) {
        return userRepository.enableUser(emailAddress);
    }

    public User addUser(User user) {

        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long userId, User user) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with the id " + userId + "was not found");
        }
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with the id " + id + " was not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteUserById(id);
    }
}
