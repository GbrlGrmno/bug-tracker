package com.gabrielgermano.bugtracker.payload.response;

import com.gabrielgermano.bugtracker.model.TicketPriority;
import com.gabrielgermano.bugtracker.model.TicketStatus;
import com.gabrielgermano.bugtracker.model.TicketType;

import java.time.LocalDateTime;

public record TicketResponse(Long id, String title, String description, TicketStatus status, TicketPriority priority,
                             TicketType type, String createdBy, LocalDateTime createdDate, String lastModifiedBy,
                             LocalDateTime lastModifiedDate) {

}
