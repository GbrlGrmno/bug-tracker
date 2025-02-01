package com.gabrielgermano.bugtracker.initializer;

import com.gabrielgermano.bugtracker.model.ERole;
import com.gabrielgermano.bugtracker.model.Role;
import com.gabrielgermano.bugtracker.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!roleRepository.existsByName(ERole.ROLE_ADMIN)) {
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
        }
        if (!roleRepository.existsByName(ERole.ROLE_MANAGER)) {
            roleRepository.save(new Role(ERole.ROLE_MANAGER));
        }
        if (!roleRepository.existsByName(ERole.ROLE_DEVELOPER)) {
            roleRepository.save(new Role(ERole.ROLE_DEVELOPER));
        }

    }
}
