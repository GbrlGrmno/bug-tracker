package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.request.AuthenticationRequest;
import com.gabrielgermano.bugtrackerbackend.response.AuthenticationResponse;
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
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody AuthenticationRequest request) {
        User registeredUser = authenticationService.register(request);
        return ResponseEntity.ok(registeredUser);


    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        User authenticatedUser = authenticationService.authenticate(request);
        String jwtToken = jwtUtil.generateToken(new UserDetailsImpl(authenticatedUser));

        return ResponseEntity.ok(AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build());

    }




}
