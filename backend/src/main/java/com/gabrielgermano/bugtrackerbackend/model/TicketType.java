package com.gabrielgermano.bugtrackerbackend.model;

public enum TicketType {

    BUG("Bug"),
    TASK("Task"),
    FEATURE("Feature"),
    ENHANCEMENT("Enhancement"),
    DOCUMENTATION("Documentation"),
    VULNERABILITY("Vulnerability");

    private String label;

    TicketType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
