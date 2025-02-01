package com.gabrielgermano.bugtracker.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2/test")
public class TestController {

    @GetMapping("/developer")
    @PreAuthorize("hasRole('DEVELOPER')")
    public String testDeveloper() {
        return "developer";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasRole('MANAGER')")
    public String testManager() {
        return "manager";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String testAdmin() {
        return "admin";
    }
}
