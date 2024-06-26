package com.gabrielgermano.bugtrackerbackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DEVELOPER')")
public class DemoController {

    @GetMapping
    public String getDemo() {
        return "Demo";
    }
}
