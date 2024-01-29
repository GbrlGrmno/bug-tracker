package com.gabrielgermano.bugtrackerbackend.repository;

import com.gabrielgermano.bugtrackerbackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
