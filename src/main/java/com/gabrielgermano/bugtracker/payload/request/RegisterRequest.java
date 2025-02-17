package com.gabrielgermano.bugtracker.payload.request;

import com.gabrielgermano.bugtracker.model.ERole;

import java.util.Set;

public class RegisterRequest {

    private String username;
    private String email;
    private String password;

    private Set<ERole> roles;


    public RegisterRequest(String username, String email, String password, Set<ERole> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<ERole> getRoles() {
        return roles;
    }

    public void setRoles(Set<ERole> roles) {
        this.roles = roles;
    }
}
