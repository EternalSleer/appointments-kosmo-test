package com.hospital.appointments.service;


import com.hospital.appointments.model.Appointment;
import com.hospital.appointments.repository.AppointmentRepository;
import com.hospital.appointments.repository.DoctorRepository;
import com.hospital.appointments.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {


    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(Appointment appointment) {

        LocalDateTime startTime = appointment.getTime();
        LocalDate date = startTime.toLocalDate();
        Long doctorId = appointment.getDoctor().getId();
        String patientName = appointment.getPatientName();
        Long officeId = appointment.getRoom().getId();


        boolean busyOffice = appointmentRepository.existsByRoom_IdAndTime(officeId, startTime);
        if (busyOffice) {
            throw new IllegalArgumentException("Ese consultorio ya tiene una cita a esa hora.");
        }


        boolean busyDoctor = appointmentRepository.existsByDoctor_IdAndTime(doctorId, startTime);
        if (busyDoctor) {
            throw new IllegalArgumentException("Ese doctor ya tiene una cita a esa hora.");
        }

        List<Appointment> sameDayPatientAppointments = appointmentRepository
                .findByPatientNameAndTimeBetween(
                        patientName,
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay()
                );

        for (Appointment a : sameDayPatientAppointments) {
            Duration diff = Duration.between(a.getTime().plusHours(1), startTime).abs();
            if (diff.toHours() < 2) {
                throw new IllegalArgumentException("El paciente ya tiene una cita cercana en el mismo día (mínimo 2 horas de diferencia).");
            }
        }


        long totalCitasDoctor = appointmentRepository.countByDoctor_IdAndTimeBetween(
                doctorId,
                date.atStartOfDay(),
                date.plusDays(1).atStartOfDay()
        );
        if (totalCitasDoctor >= 8) {
            throw new IllegalArgumentException("Ese doctor ya tiene el máximo de 8 citas para el día.");
        }

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {

        if (doctorId != null && date != null) {
            return appointmentRepository.findByDoctor_IdAndTimeBetween(
                    doctorId,
                    date.atStartOfDay(),
                    date.plusDays(1).atStartOfDay()
            );
        } else {
            return appointmentRepository.findAll();
        }

    }
}
