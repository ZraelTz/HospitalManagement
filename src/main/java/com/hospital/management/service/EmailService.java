/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.service;

import com.hospital.management.util.EmailSender;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zrael
 */

@Service
@AllArgsConstructor
public class EmailService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory.
            getLogger(EmailService.class);
    
    private final JavaMailSender mailSender;
    
    @Override
    @Async
    public void send(String to, String email) {
       try{
           MimeMessage mimeMessage = mailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = 
                   new MimeMessageHelper(mimeMessage, "utf-8");
           mimeMessageHelper.setText(email, true);
           mimeMessageHelper.setTo(to);
           mimeMessageHelper.setSubject("Confirm your email address");
           mimeMessageHelper.setFrom("testhospitalmanagement@yahoo.com");
           mailSender.send(mimeMessage);
           
       } catch(MessagingException err){
           LOGGER.error("email failed to send", err);
           throw new IllegalStateException("email failed to send");
       }
    }
    
}
