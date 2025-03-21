package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.model.ticket;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ticketRepository extends JpaRepository<ticket, Long> {
    List<ticket> findByUserId(Long userId); // Fetch tickets by user ID
}

