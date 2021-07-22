/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.exceptions;

/**
 *
 * @author Zrael
 */
public class ManagementException extends RuntimeException {
        public ManagementException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public ManagementException(String exMessage) {
        super(exMessage);
    }
}
