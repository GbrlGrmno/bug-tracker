package com.gabrielgermano.bugtracker.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectRequest(
        @NotBlank(message = "Project name cannot be empty") @Size(min = 4, max = 20, message = "Project name should have between 4 and 20 characters") String name,
        @Size(max = 100, message = "Project description should have 100 characters at most") String description) {

    public ProjectRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }


}
