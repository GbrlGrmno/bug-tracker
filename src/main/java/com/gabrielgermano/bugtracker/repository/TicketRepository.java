package com.gabrielgermano.bugtracker.repository;

import com.gabrielgermano.bugtracker.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
