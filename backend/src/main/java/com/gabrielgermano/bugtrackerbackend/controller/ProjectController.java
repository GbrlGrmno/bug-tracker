package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.model.Project;
import com.gabrielgermano.bugtrackerbackend.request.AddUserToProjectRequest;
import com.gabrielgermano.bugtrackerbackend.request.ProjectRequest;
import com.gabrielgermano.bugtrackerbackend.response.ProjectResponse;
import com.gabrielgermano.bugtrackerbackend.service.ProjectService;
import com.gabrielgermano.bugtrackerbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectRequest projectRequest) {
        return ResponseEntity.ok(projectService.createProject(projectRequest));
    }

    @GetMapping
    public List<ProjectResponse> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("{id}")
    public Project getProjectById(@PathVariable("id") Long id) {
        return projectService.getProjectById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProjectById(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
    }

//    // TODO
//    @PostMapping("/user")
//    public ResponseEntity addUserToProject(@RequestBody AddUserToProjectRequest link) {
//        projectService.addUserToProject(link.getUsername(), link.getProjectId());
//        return ResponseEntity.ok().build();
//    }

}
