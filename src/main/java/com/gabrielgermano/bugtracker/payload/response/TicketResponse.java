package com.gabrielgermano.bugtracker.payload.response;

import com.gabrielgermano.bugtracker.model.TicketPriority;
import com.gabrielgermano.bugtracker.model.TicketStatus;
import com.gabrielgermano.bugtracker.model.TicketType;

public class TicketResponse {

    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private TicketType type;

    public TicketResponse(Long id, String title, TicketStatus status, String description, TicketPriority priority, TicketType type) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.priority = priority;
        this.type = type;
    }

    public TicketResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
