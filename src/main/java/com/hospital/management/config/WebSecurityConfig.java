/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.config;

import com.hospital.management.security.JwtAuthenticationFilter;
import com.hospital.management.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zrael
 */

@Configuration
@AllArgsConstructor
@EnableWebSecurity
@Service
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private final UserService userService;
    
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtAuthenticationFilter jAuthFilter;
    
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/registration/**")
                .permitAll()
                .antMatchers("/api/registration/patient")
                .permitAll()
                .antMatchers("/api/registration/doctor")
                .permitAll()
                .antMatchers("/api/registration/nurse")
                .permitAll()
                .antMatchers("/api/doctor")
                .permitAll()
                .antMatchers("/api/nurse")
                .permitAll()
                .antMatchers("/api/patient")
                .permitAll()
                .antMatchers("/api/appointment")
                .permitAll()
                .antMatchers("/api/prescription")
                .permitAll()
                .antMatchers("/api/diagnosis")
                .permitAll()
                .antMatchers("/api/patient-medical-record")
                .permitAll()
                .antMatchers("/api/login")
                .permitAll()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        
        http.addFilterBefore(jAuthFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoPatientAuthenticationProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider daoPatientAuthenticationProvider(){
        DaoAuthenticationProvider provider = 
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
    
    
    
}
