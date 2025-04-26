package com.gabrielgermano.bugtracker.security.service;

import com.gabrielgermano.bugtracker.exception.user.UserAlreadyExistsException;
import com.gabrielgermano.bugtracker.exception.user.UserNotFoundException;
import com.gabrielgermano.bugtracker.model.Role;
import com.gabrielgermano.bugtracker.model.User;
import com.gabrielgermano.bugtracker.payload.request.LoginRequest;
import com.gabrielgermano.bugtracker.payload.request.RegisterRequest;
import com.gabrielgermano.bugtracker.payload.response.TokenResponse;
import com.gabrielgermano.bugtracker.repository.RoleRepository;
import com.gabrielgermano.bugtracker.repository.UserRepository;
import com.gabrielgermano.bugtracker.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(PasswordEncoder passwordEncoder,
                                 UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 AuthenticationManager authenticationManager,
                                 JwtUtils jwtUtils) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public TokenResponse authenticateUser(LoginRequest loginRequest) {

        logger.info("Entering the authenticateUser method");

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        logger.info("User {} authenticated successfully", loginRequest.getUsername());

        User authenticatedUser = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() ->
                new UserNotFoundException(loginRequest.getUsername()));

        logger.info("User {} found inside the repository", authenticatedUser.getUsername());

        String token = jwtUtils.generateToken(new UserDetailsImpl(authenticatedUser));

        logger.info("Generated token {}", token);

        return new TokenResponse(token);

    }


    public void registerUser(RegisterRequest registerRequest) {

        logger.info("Entering the registerUser method");

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            logger.error("User with username {} already exists", registerRequest.getUsername());
            throw new UserAlreadyExistsException("Username " + registerRequest.getUsername() + " is already taken");
        }
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            logger.error("User with email {} already exists", registerRequest.getEmail());
            throw new UserAlreadyExistsException("Email " + registerRequest.getEmail() + " is already registered");
        }

        Set<Role> roles = registerRequest.getRoles().stream()
                .map(role -> roleRepository.findByName(role)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + role)))
                .collect(Collectors.toSet());


        User newUser = new User(registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                roles);

        userRepository.save(newUser);
    }
}
