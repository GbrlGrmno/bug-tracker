package com.gabrielgermano.bugtracker.payload.request;

import com.gabrielgermano.bugtracker.model.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record RegisterRequest(
        @Size(min = 6, max = 30, message = "Username must have between 4 and 50 characters") @NotBlank(message = "Username cannot be empty") String username,
        @Email(message = "Email must be a valid email address") @NotBlank(message = "Email cannot be empty") String email,
        @Size(min = 8, message = "Password must have at least 8 characters") @NotBlank(message = "Password cannot be empty") String password,
        @Size(min = 1, max = 3, message = "Users should have between 1 and 3 roles") @NotNull(message = "Roles field cannot be null") Set<ERole> roles) {

    public RegisterRequest(String username, String email, String password, Set<ERole> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
