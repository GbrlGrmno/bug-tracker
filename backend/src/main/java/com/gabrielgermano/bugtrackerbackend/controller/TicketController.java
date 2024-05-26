package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.model.Ticket;
import com.gabrielgermano.bugtrackerbackend.request.TicketRequest;
import com.gabrielgermano.bugtrackerbackend.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("{projectId}")
    public ResponseEntity<String> createTicket(@PathVariable("projectId") Long projectId, @RequestBody TicketRequest ticketRequest) {
        ticketService.createTicket(projectId, ticketRequest);
        return ResponseEntity.ok("Ticket Created Sucessfully");
    }
}
