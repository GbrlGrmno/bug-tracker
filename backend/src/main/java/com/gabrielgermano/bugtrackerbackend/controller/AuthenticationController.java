package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.request.LoginRequest;
import com.gabrielgermano.bugtrackerbackend.request.RegistrationRequest;
import com.gabrielgermano.bugtrackerbackend.response.LoginResponse;
import com.gabrielgermano.bugtrackerbackend.response.UserResponse;
import com.gabrielgermano.bugtrackerbackend.security.JwtUtil;
import com.gabrielgermano.bugtrackerbackend.security.impl.UserDetailsImpl;
import com.gabrielgermano.bugtrackerbackend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegistrationRequest request) {
        UserResponse registeredUser = authenticationService.register(request);
        return ResponseEntity.ok(registeredUser);

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {

        User authenticatedUser = authenticationService.authenticate(request);
        String jwtToken = jwtUtil.generateToken(new UserDetailsImpl(authenticatedUser));

        return ResponseEntity.ok(
                LoginResponse.builder()
                        .token(jwtToken)
                        .build()
        );

    }




}
