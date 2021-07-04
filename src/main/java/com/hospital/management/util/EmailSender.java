/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.util;


/**
 *
 * @author Zrael
 */
public interface EmailSender {
    void send(String to, String email);
}
