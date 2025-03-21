package org.example.service;

import org.example.model.Flight;
import org.example.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getFlightsByDepartureAndArrival(String departure, String arrival) {
        return flightRepository.findByDepartureAndArrival(departure, arrival);
    }

    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long flightId) {
        flightRepository.deleteById(flightId);
    }
}