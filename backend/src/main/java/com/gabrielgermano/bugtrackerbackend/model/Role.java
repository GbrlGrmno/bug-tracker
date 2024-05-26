package com.gabrielgermano.bugtrackerbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public enum Role {

    ADMIN("Admin"),
    DEVELOPER("Developer"),
    PROJECT_MANAGER("Project Manager");

    private String role;

    Role(String role) {
        this.role = role;
    }


}
