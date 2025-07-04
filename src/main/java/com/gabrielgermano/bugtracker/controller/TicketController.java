package com.gabrielgermano.bugtracker.controller;

import com.gabrielgermano.bugtracker.payload.request.TicketRequest;
import com.gabrielgermano.bugtracker.payload.response.TicketResponse;
import com.gabrielgermano.bugtracker.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

        List<TicketResponse> tickets = ticketService.getAllTicketsFromProject(projectId);

        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketResponse>> getAllTickets(){

        List<TicketResponse> tickets = ticketService.getAllTickets();

        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable("id") Long id){

        TicketResponse ticket = ticketService.getTicketById(id);

        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    @PostMapping("/projects/{project_id}/tickets")
    public ResponseEntity<TicketResponse> createTicket(@PathVariable("project_id") Long projectId,
                                                       @RequestBody @Valid TicketRequest ticketRequest) {

        TicketResponse createdTicket = ticketService.createTicket(projectId, ticketRequest);

        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/v2/tickets/{id}")
                .buildAndExpand(createdTicket.id())
                .toUri();
        headers.setLocation(location);

        return new ResponseEntity<>(createdTicket, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") Long id){
        ticketService.deleteTicket(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/tickets/{id}")
    public ResponseEntity<TicketResponse> patchTicket(@PathVariable("id") Long ticketId, @RequestBody @Valid TicketRequest ticketRequest) {

        TicketResponse updatedTicket = ticketService.patchTicket(ticketId, ticketRequest);

        return new ResponseEntity<>(updatedTicket, HttpStatus.OK);
    }




}
