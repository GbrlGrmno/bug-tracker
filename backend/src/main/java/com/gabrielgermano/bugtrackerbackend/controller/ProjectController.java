package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.model.Project;
import com.gabrielgermano.bugtrackerbackend.request.AddMemberRequest;
import com.gabrielgermano.bugtrackerbackend.request.ProjectRequest;
import com.gabrielgermano.bugtrackerbackend.response.ProjectResponse;
import com.gabrielgermano.bugtrackerbackend.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @DeleteMapping("{id}")
    public void deleteProjectById(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
    }

    @PostMapping("{project_id}/add")
    public ResponseEntity<String> addProjectMember(@PathVariable("project_id") Long projectId, @RequestBody AddMemberRequest request) {
        projectService.addProjectMember(projectId, request);
        return ResponseEntity.ok("User added sucessfully");
    }



}
