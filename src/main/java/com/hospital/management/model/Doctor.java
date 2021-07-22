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
public class Doctor implements UserDetails {

    //Database table sequence generators
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

    //Patient Details
    private Long id;

    @NotBlank(message = "first name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @NotBlank(message = "your middle name is required")
    private String otherNames;

    @NotBlank(message = "your address is required")
    private String address;

    @NotBlank(message = "country is required")
    private String country;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "your state is required")
    private String countryState;

    @NotBlank(message = "your email is required")
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "your date of birth is required")
    private String dob;

    @NotBlank(message = "required")
    private String gender;
    
    @NotBlank(message = "your department is required")
    private String department;

    @NotBlank(message = "your phone number is required")
    private String phone;
    
    @Enumerated(EnumType.STRING)
    private AppUserRole userRole = AppUserRole.DOCTOR;

    //Account Details
    private Boolean locked = false;
    private Boolean enabled = false;
    private Boolean approvedStatus = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority
                = new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    //Class constructor
    public Doctor(String firstName, String lastName, String otherNames, String address, String country, String city, String countryState, String email, String password, String dob, String gender, String department, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherNames = otherNames;
        this.address = address;
        this.country = country;
        this.city = city;
        this.countryState = countryState;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.department = department;
        this.phone = phone;
    }

    //Class getters
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

    public String getFirstName() {
        return firstName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getCountryState() {
        return countryState;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public Boolean getApprovedStatus() {
        return approvedStatus;
    }

    public AppUserRole getUserRole() {
        return userRole;
    }
    
    public String getDepartment() {
        return department;
    }

    public String getPhone() {
        return phone;
    }

    //Account details returned
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
