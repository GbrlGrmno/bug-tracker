package com.gabrielgermano.bugtrackerbackend.controller;

import com.gabrielgermano.bugtrackerbackend.model.Ticket;
import com.gabrielgermano.bugtrackerbackend.model.TicketType;
import com.gabrielgermano.bugtrackerbackend.request.TicketRequest;
import com.gabrielgermano.bugtrackerbackend.request.UpdateTicketPriorityRequest;
import com.gabrielgermano.bugtrackerbackend.request.UpdateTicketStatusRequest;
import com.gabrielgermano.bugtrackerbackend.request.UpdateTicketTypeRequest;
import com.gabrielgermano.bugtrackerbackend.response.TicketResponse;
import com.gabrielgermano.bugtrackerbackend.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/ticket")
@RestController
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketResponse> getTicketById(@PathVariable("ticketId") Long ticketId) {
        return ResponseEntity.ok(ticketService.getTicketById(ticketId));

    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<?> deleteTicketById(@PathVariable("ticketId") Long ticketId) {
        ticketService.deleteTicketById(ticketId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{ticketId}/type")
    public ResponseEntity<TicketResponse> updateTicketType(@PathVariable("ticketId") Long ticketId, @RequestBody UpdateTicketTypeRequest request) {
        return ResponseEntity.ok(ticketService.updateTicketType(ticketId, request.getType()));
    }

    @PatchMapping("/{ticketId}/status")
    public ResponseEntity<TicketResponse> updateTicketStatus(@PathVariable("ticketId") Long ticketId, @RequestBody UpdateTicketStatusRequest request) {
        return ResponseEntity.ok(ticketService.updateTicketStatus(ticketId, request.getStatus()));
    }

    @PatchMapping("/{ticketId}/priority")
    public ResponseEntity<TicketResponse> updateTicketPriority(@PathVariable("ticketId") Long ticketId, @RequestBody UpdateTicketPriorityRequest request) {
        return ResponseEntity.ok(ticketService.updateTicketPriority(ticketId, request.getPriority()));
    }



}
