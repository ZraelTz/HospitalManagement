/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.dto.AuthenticationResponse;
import com.hospital.management.dto.DoctorRegistrationRequest;
import com.hospital.management.dto.LoginRequest;
import com.hospital.management.dto.NurseRegistrationRequest;
import com.hospital.management.dto.PatientRegistrationRequest;
import com.hospital.management.model.Patient;
import com.hospital.management.model.AppUserRole;
import com.hospital.management.util.EmailSender;
import com.hospital.management.util.EmailValidator;
import com.hospital.management.model.ConfirmationToken;
import com.hospital.management.model.Doctor;
import com.hospital.management.model.Nurse;
import com.hospital.management.security.JwtProvider;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zrael
 */

@Service
@AllArgsConstructor
@Configuration
public class AuthService {
    
    private final PatientService patientService;
    private final NurseService nurseService;
    private final DoctorService doctorService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private static String username;
    
    //register patient with PatientRegistrationRequest dto
    public ResponseEntity<String> registerPatient(PatientRegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        
        String token = patientService.signUpPatient(new Patient(
                request.getFirstName(),
                request.getLastName(),
                request.getOtherNames(),
                request.getAddress(),
                request.getCountry(),
                request.getCity(),
                request.getCountryState(),
                request.getEmail(),
                request.getPassword(),
                request.getDob(),
                request.getGender(),
                request.getPhone()
            )         
        );
        String link = "http://localhost:8085/api/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(), 
                buildEmail(request.getFirstName(), link));
        return new ResponseEntity<>("Your Patient Account Registration was successfull,"
                + " check your email for verification link", HttpStatus.OK);
    }
    
    //register doctor with DoctorRegistrationRequest dto
    public ResponseEntity<String> registerDoctor(DoctorRegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = doctorService.signUpDoctor(new Doctor(
                request.getFirstName(),
                request.getLastName(),
                request.getOtherNames(),
                request.getAddress(),
                request.getCountry(),
                request.getCity(),
                request.getCountryState(),
                request.getEmail(),
                request.getPassword(),
                request.getDob(),
                request.getGender(),
                request.getDepartment(),
                request.getPhone()
        )
        );
        String link = "http://localhost:8085/api/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(),
                buildEmail(request.getFirstName(), link));
        return new ResponseEntity<>("Your Docotor Account Registration was successfull,"
                + " check your email for verification link", HttpStatus.OK);
    }
    
    //register nurse with NurseRegistrationRequest dto
    public ResponseEntity<String> registerNurse(NurseRegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = nurseService.signUpNurse(new Nurse(
                request.getFirstName(),
                request.getLastName(),
                request.getOtherNames(),
                request.getAddress(),
                request.getCountry(),
                request.getCity(),
                request.getCountryState(),
                request.getEmail(),
                request.getPassword(),
                request.getDob(),
                request.getGender(),
                request.getDepartment(),
                request.getPhone()
        )
        );
        String link = "http://localhost:8085/api/registration/confirm?token=" + token;
        emailSender.send(request.getEmail(),
                buildEmail(request.getFirstName(), link));
        return new ResponseEntity<>("Your Nurse Account Registration was successfull,"
                + " check your email for verification link", HttpStatus.OK);
    }
    
    //confirm token
    @Transactional
    public ResponseEntity<String> confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }
        
        if(confirmationToken.getDoctor().getEmail() != null){
                confirmationTokenService.setConfirmedAt(token);
                doctorService.enableDoctor(
                        confirmationToken.getDoctor().getEmail());
                return new ResponseEntity<>("Your Doctor Account has been Activated Successfully, "
                        + "Your account would be reveiwed and approved by the Admin",
                        HttpStatus.OK);
        }

        if (confirmationToken.getNurse().getEmail() != null) {
                confirmationTokenService.setConfirmedAt(token);
                nurseService.enableNurse(
                        confirmationToken.getNurse().getEmail());
                return new ResponseEntity<>("Your Nurse Account has been Activated Successfully, "
                        + "Your account would be reveiwed and approved by the Admin",
                         HttpStatus.OK);
        }
        
        if (confirmationToken.getPatient().getEmail() != null) {
                confirmationTokenService.setConfirmedAt(token);
                patientService.enablePatient(
                        confirmationToken.getPatient().getEmail());
                return new ResponseEntity<>("Your Patient Account has been Activated Successfully",
                         HttpStatus.OK);
        }
        
                return new ResponseEntity<>("An error occurred while trying to activate your account, please try again",
                        HttpStatus.EXPECTATION_FAILED);
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
                loginRequest.getPassword()));
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String jwtToken = jwtProvider.generateToken(authentication);
        username = loginRequest.getUsername();
        
        if (patientService.findPatientDetails(username) != null)
            return new AuthenticationResponse(jwtToken, 
                patientService.findPatientDetails(username));
        
        if (doctorService.findDoctorDetails(username) != null)
            return new AuthenticationResponse(jwtToken,
                    doctorService.findDoctorDetails(username));
        
        if (nurseService.findNurseDetails(username) != null)
            return new AuthenticationResponse(jwtToken,
                    nurseService.findNurseDetails(username));
        
        return new AuthenticationResponse(null, null);
    }
    
    public AppUserRole getRole(){
        if(patientService.findUserRole(username) != null)
            return patientService.findUserRole(username);
        
        if (doctorService.findUserRole(username) != null)
            return nurseService.findUserRole(username);
        
        if (nurseService.findUserRole(username) != null)
            return nurseService.findUserRole(username);
        
        return null;
    }
}
