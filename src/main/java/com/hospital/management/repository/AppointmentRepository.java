package com.hospital.management.repository;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.hospital.management.model.Appointment;
import com.hospital.management.model.AppointmentStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Zrael
 */


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientUserEmail(String patientEmail);
    List<Appointment> findByDoctorUserEmail(String doctorEmail);
    List<Appointment> findByNurseUserEmail(String doctorEmail);

    @Transactional
    @Modifying
    @Query("UPDATE Appointment a "
            + "SET a.status = ?2 "
            + "WHERE a.id = ?1")
    int fulfilAppointment(Long appointmentId, AppointmentStatus appointmentStatus);
    
    @Query("UPDATE Appointment a "
            + "SET a.status = ?2 "
            + "WHERE a.id = ?1")
    int cancelAppointment(Long appointmentId, AppointmentStatus appointmentStatus);
}

    
