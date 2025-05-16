package org.example.dto;

import lombok.Data;

@Data
public class FlightDTO {
    private Long id;
    private String flightNumber;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;

    
}
