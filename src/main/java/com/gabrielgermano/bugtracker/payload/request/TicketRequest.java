package com.gabrielgermano.bugtracker.payload.request;

import com.gabrielgermano.bugtracker.model.TicketPriority;
import com.gabrielgermano.bugtracker.model.TicketStatus;
import com.gabrielgermano.bugtracker.model.TicketType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class TicketRequest {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters long")
    private String title;

    @Size(max = 1000, message = "Description can be at most 1000 characters long")
    private String description;

    @NotNull(message = "Status cannot be null")
    private TicketStatus status;

    @NotNull(message = "Priority cannot be null")
    private TicketPriority priority;

    @NotNull(message = "Type cannot be null")
    private TicketType type;

    public TicketRequest(String title, String description, TicketStatus status, TicketPriority priority, TicketType type) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public TicketPriority getPriority() {
        return priority;
    }

    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }
}
