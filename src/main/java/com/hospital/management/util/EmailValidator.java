/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.util;

import com.hospital.management.dto.Username;
import java.util.function.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zrael
 */

@Service
public class EmailValidator implements Predicate<String>{

    @Override
    public boolean test(String t) {
        //TODO: Regex to validate
       return true;
    }
    
}
