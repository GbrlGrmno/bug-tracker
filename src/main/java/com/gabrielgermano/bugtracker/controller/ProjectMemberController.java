package com.gabrielgermano.bugtracker.controller;

import com.gabrielgermano.bugtracker.model.User;
import com.gabrielgermano.bugtracker.payload.response.ProjectResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
        List<UserResponse> users = projectMemberService.addUserToProject(request.projectId(), request.userId());

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(params = "projectId")
    public ResponseEntity<List<UserResponse>> getAllUsersFromProject(@RequestParam("projectId") Long projectId) {
        List<UserResponse> users = projectMemberService.getAllUsersFromProject(projectId);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(params = "userId")
    public ResponseEntity<List<ProjectResponse>> getAllProjectsFromUser(@RequestParam("userId") Long userId) {
        List<ProjectResponse> projects = projectMemberService.getAllProjectFromUser(userId);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserFromProject(@RequestBody @Valid ProjectMemberRequest request) {
        projectMemberService.deleteUserFromProject(request.projectId(), request.userId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
