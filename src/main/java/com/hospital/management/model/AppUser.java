/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.model;

import java.util.Collection;
import java.util.Collections;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Zrael
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails{
    
    
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )   
    
    private Long id;
    @NotBlank(message = "last name is required")
    private String lastName;
    @NotBlank(message = "first name is required")
    private String firstName;
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "cpr number is required")
    private String userId;
    @NotBlank(message = "required")
    private String gender;
    
    private String symptom;
    
    private String dept;
    
    private String doctorId;
    
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "user role is required")
    private AppUserRole userRole;

    private Boolean locked = false;
    private Boolean enabled = false;
    private Boolean approvedStatus = false;


    public AppUser(String lastName, String firstName, String email, String password, String userId, String gender, String symptom, String dept, String doctorId, AppUserRole userRole) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.gender = gender;
        this.symptom = symptom;
        this.dept = dept;
        this.doctorId = doctorId;
        this.userRole = userRole;
    }
    

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        SimpleGrantedAuthority authority 
                = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
       return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

        public String getLastName() {
        return lastName;
    }
       
        public Boolean getApprovedStatus() {
        return approvedStatus;
    }
        
        public String getFirstName() {
        return firstName;
    }
        
        public AppUserRole role() {
        return userRole;
    }
        
        public String getUserId() {
        return userId;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
    
    
}
