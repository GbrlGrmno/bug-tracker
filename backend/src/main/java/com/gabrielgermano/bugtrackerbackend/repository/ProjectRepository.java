package com.gabrielgermano.bugtrackerbackend.repository;

import com.gabrielgermano.bugtrackerbackend.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p ORDER BY p.createdAt DESC")
    List<Project> findAllByOrderByCreatedAtDesc();

    @Query("SELECT p FROM Project p WHERE p.id = :id")
    Optional<Project> findById(Long id);
}
