package org.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @NotNull
    private String flightNumber;

    @NotNull
    private String departure;

    @NotNull
    private String arrival;

    private LocalDateTime departureTime;
    private LocalDateTime qarrivalTime;
}
