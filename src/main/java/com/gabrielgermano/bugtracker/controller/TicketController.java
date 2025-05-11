package com.gabrielgermano.bugtracker.controller;

import com.gabrielgermano.bugtracker.payload.request.TicketRequest;
import com.gabrielgermano.bugtracker.payload.response.TicketResponse;
import com.gabrielgermano.bugtracker.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class TicketController {

    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/projects/{project_id}/tickets")
    public ResponseEntity<List<TicketResponse>> getAllTicketsFromProject(@PathVariable("project_id") Long projectId){
        return ResponseEntity.ok(ticketService.getAllTicketsFromProject(projectId));
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketResponse>> getAllTickets(){
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable("id") Long id){
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    @PostMapping("/projects/{project_id}/tickets")
    public ResponseEntity<TicketResponse> createTicket(@PathVariable("project_id") Long projectId,
                                                       @RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.createTicket(projectId, ticketRequest));
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") Long id){
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/tickets/{id}")
    public ResponseEntity<TicketResponse> patchTicket(@PathVariable("id") Long ticketId, @RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketService.patchTicket(ticketId, ticketRequest));
    }




}
