package com.gabrielgermano.bugtracker.controller;

import com.gabrielgermano.bugtracker.payload.request.LoginRequest;
import com.gabrielgermano.bugtracker.payload.request.RegisterRequest;
import com.gabrielgermano.bugtracker.payload.response.MessageResponse;
import com.gabrielgermano.bugtracker.payload.response.TokenResponse;
import com.gabrielgermano.bugtracker.security.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Entering the login controller.");
        return ResponseEntity.ok(
                authenticationService.authenticateUser(loginRequest)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        logger.info("Entering the register controller.");
        authenticationService.registerUser(registerRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully"));
    }
}
