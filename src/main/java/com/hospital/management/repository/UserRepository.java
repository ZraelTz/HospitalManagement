/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.repository;

import com.hospital.management.model.User;
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
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long id);
    void deleteUserById(Long id);
    
    @Transactional
    @Modifying
    @Query("UPDATE User u "
            + "SET u.enabled = TRUE WHERE u.email = ?1")
    int enableUser(String email);

}
