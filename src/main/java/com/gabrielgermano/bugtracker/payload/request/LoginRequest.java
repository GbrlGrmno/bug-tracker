package com.gabrielgermano.bugtracker.payload.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(message = "Username cannot be empty") String username,
                           @NotBlank(message = "Password is required for login") String password) {

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
