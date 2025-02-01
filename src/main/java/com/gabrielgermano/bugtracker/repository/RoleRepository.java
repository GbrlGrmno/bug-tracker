package com.gabrielgermano.bugtracker.repository;

import com.gabrielgermano.bugtracker.model.ERole;
import com.gabrielgermano.bugtracker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

    boolean existsByName(ERole name);
}
