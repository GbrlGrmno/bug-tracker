package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.response.UserResponse;
import com.gabrielgermano.bugtrackerbackend.security.impl.UserDetailsImpl;
import com.gabrielgermano.bugtrackerbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

//    @GetMapping("/{username}")
//    public ResponseEntity<UserResponse> findUserByUsername(@PathVariable("username") String username) {
//        return ResponseEntity.ok(userService.findUserByUsername(username));
//    }


}