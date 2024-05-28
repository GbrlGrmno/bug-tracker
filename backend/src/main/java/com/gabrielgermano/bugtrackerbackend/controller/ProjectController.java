package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.model.Project;
import com.gabrielgermano.bugtrackerbackend.model.ProjectMember;
import com.gabrielgermano.bugtrackerbackend.request.AddMemberRequest;
import com.gabrielgermano.bugtrackerbackend.request.ProjectRequest;
import com.gabrielgermano.bugtrackerbackend.request.TicketRequest;
import com.gabrielgermano.bugtrackerbackend.response.ProjectResponse;
import com.gabrielgermano.bugtrackerbackend.response.TicketResponse;
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
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final TicketService ticketService;

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

    @PostMapping("{project_id}/members")
    public ResponseEntity<String> addProjectMember(@PathVariable("project_id") Long projectId, @RequestBody AddMemberRequest request) {
        projectService.addProjectMember(projectId, request);
        return ResponseEntity.ok("User added sucessfully");
    }

    @GetMapping("{project_id}/members")
    public ResponseEntity<Set<ProjectMember>> getAllMembers(@PathVariable("project_id") Long projectId) {
        return ResponseEntity.ok(projectService.getAllMembers(projectId));
    }

    @GetMapping("/{projectId}/tickets")
    public ResponseEntity<List<TicketResponse>> getAllTickets(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(ticketService.getAllTicketsByProject(projectId));
    }
    @PostMapping("/{projectId}/tickets")
    public ResponseEntity<TicketResponse> createTicket(@PathVariable("projectId") Long projectId, @RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(ticketService.createTicket(projectId,ticketRequest));
    }





}
