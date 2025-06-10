package com.gabrielgermano.bugtracker.controller;

import com.gabrielgermano.bugtracker.payload.request.ProjectRequest;
import com.gabrielgermano.bugtracker.payload.response.MessageResponse;
import com.gabrielgermano.bugtracker.payload.response.ProjectResponse;
import com.gabrielgermano.bugtracker.service.ProjectService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectRequest projectRequest) {
        logger.info("Entering createProject method inside the controller layer");
        return ResponseEntity.ok(projectService.createProject(projectRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        logger.info("Entering getAllProjects method inside the controller layer");
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        logger.info("Entering getProject method inside the controller layer");
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest projectRequest) {
        logger.info("Entering updateProject method inside the controller layer");
        return ResponseEntity.ok(projectService.updateProjectById(id, projectRequest));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteProject(@PathVariable Long id) {
        logger.info("Entering deleteProject method inside the controller layer");
        return ResponseEntity.ok(projectService.deleteProjectById(id));
    }
}
