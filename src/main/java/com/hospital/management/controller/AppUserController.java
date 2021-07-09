/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.controller;

import com.hospital.management.model.AppUser;
import com.hospital.management.model.AppUserRole;
import com.hospital.management.service.AppUserService;
import com.hospital.management.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Zrael
 */
@RestController
@RequestMapping("api/appusers")
public class AppUserController {

    private final AuthService authService;
    private final AppUserService appUserService;
    

    public AppUserController(AppUserService appUserService, AuthService authService) {
        this.appUserService = appUserService;
        this.authService = authService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppUser>> getAllAppUsers() {
        if(!authService.getRole().equals(AppUserRole.ADMIN)){
            List<AppUser> appUsers = null;
            return new ResponseEntity<>(appUsers, HttpStatus.FORBIDDEN);
        }
        List<AppUser> appUser = appUserService.findAllAppUsers();
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<AppUser> getAppUserById(@PathVariable("id") Long id) {
        if(!authService.getRole().equals(AppUserRole.ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        AppUser appUser = appUserService.findAppUserById(id);
        return new ResponseEntity<>(appUser, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AppUser> addAppUser(@RequestBody AppUser appUser) {
        if (!authService.getRole().equals(AppUserRole.ADMIN)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        AppUser newAppUser = appUserService.addAppUser(appUser);
        return new ResponseEntity<>(newAppUser, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AppUser> updateAppUser(@RequestBody AppUser appUser) {
        if (!authService.getRole().equals("ADMIN")) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        AppUser updateAppUser = appUserService.updateAppUser(appUser);
        return new ResponseEntity<>(updateAppUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppUser(@PathVariable("id") Long id) {
        if (!authService.getRole().equals(AppUserRole.ADMIN)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        appUserService.deleteAppUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
