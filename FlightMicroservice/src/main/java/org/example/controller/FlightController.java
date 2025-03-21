package org.example.controller;

import org.example.model.Flight;
import org.example.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/schedule")
    public ResponseEntity<List<Flight>> getFlightSchedule(
            @RequestParam String departure,
            @RequestParam String arrival) {
        List<Flight> flights = flightService.getFlightsByDepartureAndArrival(departure, arrival);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        Flight addedFlight = flightService.addFlight(flight);
        return new ResponseEntity<>(addedFlight, HttpStatus.CREATED);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long flightId) {
        flightService.deleteFlight(flightId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}