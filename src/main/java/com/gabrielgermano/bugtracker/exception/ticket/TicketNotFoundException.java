package com.gabrielgermano.bugtracker.exception.ticket;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Ticket with id " + id + " not found");
    }
}
