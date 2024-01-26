package com.gabrielgermano.bugtrackerbackend.security.impl;

import com.gabrielgermano.bugtrackerbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findUserByUsername(username);

        return user.map(UserDetailsImpl::new).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
