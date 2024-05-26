package com.gabrielgermano.bugtrackerbackend.service;

import com.gabrielgermano.bugtrackerbackend.model.User;
import com.gabrielgermano.bugtrackerbackend.repository.UserRepository;
import com.gabrielgermano.bugtrackerbackend.response.UserResponse;
import com.gabrielgermano.bugtrackerbackend.security.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public List<UserResponse> findAll() {
        return Arrays.asList(modelMapper.map(userRepository.findAll(), UserResponse[].class));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getId();
    }

    public User getCurrentUser() {
        return userRepository.findById(getCurrentUserId()).orElseThrow();
    }
}
