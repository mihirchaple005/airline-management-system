package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserId(Long userId); // Fetch tickets by user ID
}

