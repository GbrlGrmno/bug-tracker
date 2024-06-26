package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.model.Project;
import com.gabrielgermano.bugtrackerbackend.model.ProjectMember;
import com.gabrielgermano.bugtrackerbackend.request.AddMemberRequest;
import com.gabrielgermano.bugtrackerbackend.request.ProjectRequest;
import com.gabrielgermano.bugtrackerbackend.request.TicketRequest;
import com.gabrielgermano.bugtrackerbackend.response.*;
import com.gabrielgermano.bugtrackerbackend.service.ProjectService;
import com.gabrielgermano.bugtrackerbackend.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
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

    @GetMapping("{projectId}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable("projectId") Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @DeleteMapping("{projectId}")
    public void deleteProjectById(@PathVariable("projectId") Long id) {
        projectService.deleteProject(id);
    }

    @PostMapping("{projectId}/members")
    public ResponseEntity<AddMemberResponse> addProjectMember(@PathVariable("projectId") Long projectId, @RequestBody AddMemberRequest request) {
        return ResponseEntity.ok(projectService.addProjectMember(projectId, request));
    }

    @GetMapping("{project_id}/members")
    public ResponseEntity<List<UserResponse>> getAllMembers(@PathVariable("project_id") Long projectId) {
        return ResponseEntity.ok(projectService.getAllMembers(projectId));
    }

   @DeleteMapping("{projectId}/members/{userId}")
   public ResponseEntity<?> removeUserFromProject(@PathVariable("projectId") Long projectId, @PathVariable("userId") Long userId) {
        projectService.removeUserFromProject(projectId, userId);
        return ResponseEntity.ok().build();
   }


}
