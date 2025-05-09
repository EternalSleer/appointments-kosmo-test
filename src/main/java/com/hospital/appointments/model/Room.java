package com.hospital.appointments.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "room")
public class Room {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int number;
    @Column
    private int floor;
}
