package org.example.repository;

import org.example.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    Flight getFlightById(long flightId);

    List<Flight> findByDepartureAndArrival(String departure, String arrival);
}
