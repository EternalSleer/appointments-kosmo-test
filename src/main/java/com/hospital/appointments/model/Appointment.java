package com.hospital.appointments.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Appointment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Room room;

    @Column
    private LocalDateTime time;

    @Column
    private String patientName;


}
