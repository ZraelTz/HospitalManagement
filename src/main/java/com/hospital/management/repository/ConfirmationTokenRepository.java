/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.repository;

import com.hospital.management.model.ConfirmationToken;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zrael
 */

@Repository
public interface ConfirmationTokenRepository 
        extends JpaRepository<ConfirmationToken, Long>{
    
    Optional<ConfirmationToken> findByToken(String token);
    
    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c "
            + "SET c.confirmedAt = ?2 "
            + "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
            LocalDateTime confirmedAt);
}
