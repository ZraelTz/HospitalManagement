/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.repository;

import com.hospital.management.model.Nurse;
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
public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Optional<Nurse> findByEmail(String email);

    Optional<Nurse> findNurseById(Long id);

    void deleteNurseById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Patient a "
            + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableNurse(String email);

}
