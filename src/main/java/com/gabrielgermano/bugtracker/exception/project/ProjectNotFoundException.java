package com.gabrielgermano.bugtracker.exception.project;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException(Long id) {
        super("Project with id " + id + " not found");
    }
}
