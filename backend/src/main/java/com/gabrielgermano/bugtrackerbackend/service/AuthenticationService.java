package com.gabrielgermano.bugtrackerbackend.service;

import com.gabrielgermano.bugtrackerbackend.model.Role;
import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.repository.UserRepository;
import com.gabrielgermano.bugtrackerbackend.request.AuthenticationRequest;
import com.gabrielgermano.bugtrackerbackend.response.AuthenticationResponse;
import com.gabrielgermano.bugtrackerbackend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        return userRepository.findByUsername(request.getUsername()).orElseThrow();
    }

    public User register(AuthenticationRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.DEVELOPER)
                .build();

        return userRepository.save(user);

    }




}
