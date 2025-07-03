package com.gabrielgermano.bugtracker.payload.request;

import com.gabrielgermano.bugtracker.model.TicketPriority;
import com.gabrielgermano.bugtracker.model.TicketStatus;
import com.gabrielgermano.bugtracker.model.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record TicketRequest(
        @NotBlank(message = "Title cannot be empty") @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters long") String title,
        @Size(max = 1000, message = "Description can be at most 1000 characters long") String description,
        @NotNull(message = "Status cannot be null") TicketStatus status,
        @NotNull(message = "Priority cannot be null") TicketPriority priority,
        @NotNull(message = "Type cannot be null") TicketType type) {

    public TicketRequest(String title, String description, TicketStatus status, TicketPriority priority, TicketType type) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.type = type;
    }

}
