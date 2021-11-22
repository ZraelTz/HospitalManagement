/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.dto;

import com.hospital.management.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Zrael
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String username;
    private UserRole userRole;
    private Boolean approvedStatus;
    private Boolean enabledStatus;
    private Long userId;
}
