package com.gabrielgermano.bugtracker.controller;

import com.gabrielgermano.bugtracker.payload.request.LoginRequest;
import com.gabrielgermano.bugtracker.payload.request.RegisterRequest;
import com.gabrielgermano.bugtracker.payload.response.MessageResponse;
import com.gabrielgermano.bugtracker.payload.response.TokenResponse;
import com.gabrielgermano.bugtracker.payload.response.UserResponse;
import com.gabrielgermano.bugtracker.service.AuthenticationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v2/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        logger.info("Entering the login controller.");

        TokenResponse token = authenticationService.authenticateUser(loginRequest);

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        logger.info("Entering the register controller.");

        UserResponse user = authenticationService.registerUser(registerRequest);

        HttpHeaders header = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.
                fromCurrentContextPath().
                path("api/v2/users/{id}").
                buildAndExpand(user.id()).
                toUri();
        header.setLocation(location);

        return new ResponseEntity<>(user, header, HttpStatus.CREATED);

    }
}
