package com.gabrielgermano.bugtrackerbackend.model;

public enum TicketStatus {

    OPEN("Open"),
    IN_PROGRESS("In progress"),
    UNDER_REVIEW("Under review"),
    RESOLVED("Resolved"),
    CLOSED("Done"),
    REOPENED("Reopened"),
    ON_HOLD("On hold"),
    PENDING_FEEDBACK("Pending Feedback");

    private String label;

    TicketStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
