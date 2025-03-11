package com.gabrielgermano.bugtracker.repository;

import com.gabrielgermano.bugtracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
