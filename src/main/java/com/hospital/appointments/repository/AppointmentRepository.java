package com.hospital.appointments.repository;


import com.hospital.appointments.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByRoom_IdAndTime(Long roomId, LocalDateTime time);

    boolean existsByDoctor_IdAndTime(Long doctorId, LocalDateTime time);

    List<Appointment> findByPatientNameAndTimeBetween(String patientName, LocalDateTime start, LocalDateTime end);

    long countByDoctor_IdAndTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByDoctor_IdAndTimeBetween(Long doctorId, LocalDateTime start, LocalDateTime end);

    List<Appointment> findByRoom_IdAndTimeBetween(Long roomId, LocalDateTime start, LocalDateTime end);
}
