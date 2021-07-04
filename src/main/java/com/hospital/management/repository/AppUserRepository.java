/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.repository;

import com.hospital.management.model.AppUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Zrael
 */

@Repository
@EnableJpaRepositories
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findAppUserById(Long id);
    void deleteAppUserById(Long id);
    
    @Transactional
    @Modifying
    @Query("UPDATE AppUser a "
            + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);

}
