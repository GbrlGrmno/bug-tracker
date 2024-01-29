package com.gabrielgermano.bugtrackerbackend.model;

public enum TicketPriority {

    IMMEDIATE("Immediate"),
    CRITICAL("Critical"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private String label;

    TicketPriority(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
