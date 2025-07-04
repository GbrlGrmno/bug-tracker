package com.gabrielgermano.bugtracker.controller;

import com.gabrielgermano.bugtracker.payload.request.ProjectRequest;
import com.gabrielgermano.bugtracker.payload.response.ProjectResponse;
import com.gabrielgermano.bugtracker.service.ProjectService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        ProjectResponse createdProject = projectService.createProject(projectRequest);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProject.id())
                .toUri();
        headers.setLocation(location);

        return new ResponseEntity<>(createdProject, headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        logger.info("Entering getAllProjects method inside the controller layer");

        List<ProjectResponse> projects = projectService.getAllProjects();

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        logger.info("Entering getProject method inside the controller layer");

        ProjectResponse project = projectService.getProjectById(id);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequest projectRequest) {
        logger.info("Entering updateProject method inside the controller layer");

        ProjectResponse updatedProject = projectService.updateProjectById(id, projectRequest);

        return new ResponseEntity<>(updatedProject, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        logger.info("Entering deleteProject method inside the controller layer");
        projectService.deleteProjectById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
