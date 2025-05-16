package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.example.dto.FlightDTO;  // Create a simple DTO for flight info

@FeignClient(name = "flight-microservice", url = "http://localhost:8082")
public interface FlightClient {
    @GetMapping("/api/flights/{id}")
    FlightDTO getFlightById(@PathVariable("id") Long id);

    @GetMapping("/flights/exists/{flightId}")
    Boolean doesFlightExist(@PathVariable Long flightId);
}
