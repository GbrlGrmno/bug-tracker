package com.gabrielgermano.bugtracker.controller;

import com.gabrielgermano.bugtracker.payload.response.ProjectResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.gabrielgermano.bugtracker.payload.request.ProjectMemberRequest;
import com.gabrielgermano.bugtracker.payload.response.UserResponse;
import com.gabrielgermano.bugtracker.service.ProjectMemberService;

import java.util.List;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v2/project-members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    public ProjectMemberController(ProjectMemberService projectMemberService) {
        this.projectMemberService = projectMemberService;
    }

    @PostMapping
    public ResponseEntity<List<UserResponse>> addUserToProject(@RequestBody @Valid ProjectMemberRequest request) {
        return ResponseEntity.ok(projectMemberService.addUserToProject(request.getProjectId(), request.getUserId()));
    }

    @GetMapping(params = "projectId")
    public ResponseEntity<List<UserResponse>> getAllUsersFromProject(@RequestParam("projectId") Long projectId) {
        return ResponseEntity.ok(projectMemberService.getAllUsersFromProject(projectId));
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsFromUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(projectMemberService.getAllProjectFromUser(userId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserFromProject(@RequestBody @Valid ProjectMemberRequest request) {
        projectMemberService.deleteUserFromProject(request.getProjectId(), request.getUserId());
        return ResponseEntity.noContent().build();
    }
    
}
