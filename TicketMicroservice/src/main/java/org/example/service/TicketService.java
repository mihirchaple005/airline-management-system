package org.example.service;

import org.example.model.ticket;
import org.example.repository.ticketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ticketRepository ticketRepository;

//    @Autowired
//    private UserServiceClient userServiceClient; // To call User Microservice

    public ticket bookTicket(ticket ticket) {
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

    public List<ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }
}