package com.gabrielgermano.bugtracker.controller;

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
    public ResponseEntity<List<UserResponse>> addUserToProject(@RequestBody ProjectMemberRequest request) {
        return ResponseEntity.ok(projectMemberService.addUserToProject(request.getProjectId(), request.getUserId()));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsersFromProject(@RequestParam("projectId") Long projectId) {
        return ResponseEntity.ok(projectMemberService.getAllUsersFromProject(projectId));
    }
    
}
