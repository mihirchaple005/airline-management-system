package org.example.service;

import org.example.model.Ticket;
import org.example.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.client.UserClient;
import org.example.client.FlightClient;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    private UserClient userClient;

    private FlightClient flightClient;

//    @Autowired
//    private UserServiceClient userServiceClient; // To call User Microservice

    public Ticket bookTicket(Ticket ticket) {
        Boolean userExists = userClient.doesUserExist(ticket.getUserId());
        if (userExists == null || !userExists) {
            throw new RuntimeException("User does not exist");
        }

        if (!flightClient.doesFlightExist(ticket.getFlightId())) {
            throw new RuntimeException("Flight not found");
        }
        // Validate if the user exists
//        Boolean userExists = userServiceClient.doesUserExist(ticket.getUserId()).block();
//        if (userExists == null || !userExists) {
//            throw new RuntimeException("User does not exist");
//        }
        return ticketRepository.save(ticket);
    }

    public void cancelTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }
}