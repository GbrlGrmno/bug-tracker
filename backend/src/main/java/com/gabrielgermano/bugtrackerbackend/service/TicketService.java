package com.gabrielgermano.bugtrackerbackend.service;

import com.gabrielgermano.bugtrackerbackend.model.*;
import com.gabrielgermano.bugtrackerbackend.repository.ProjectRepository;
import com.gabrielgermano.bugtrackerbackend.repository.TicketRepository;
import com.gabrielgermano.bugtrackerbackend.request.TicketRequest;
import com.gabrielgermano.bugtrackerbackend.response.TicketResponse;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final ProjectRepository projectRepository;

    private final TicketRepository ticketRepository;

    private final ModelMapper modelMapper;
    public TicketResponse createTicket(Long projectId, TicketRequest ticketRequest) {

        Project project = projectRepository.findById(projectId).orElseThrow();

        Ticket ticket = modelMapper.map(ticketRequest, Ticket.class);

        ticket.setProject(project);

       return modelMapper.map(ticketRepository.save(ticket), TicketResponse.class);

    }

    public List<TicketResponse> getAllTicketsByProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow();
        return Arrays.asList(modelMapper.map(project.getTickets(), TicketResponse[].class));
    }

    public TicketResponse getTicketById(Long ticketId) {
        return modelMapper.map(ticketRepository.findById(ticketId), TicketResponse.class);
    }

    public void deleteTicketById(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    public TicketResponse updateTicketType(Long ticketId, TicketType type) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticket.setType(type);
        ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketResponse.class);
    }

    public TicketResponse updateTicketStatus(Long ticketId, TicketStatus status) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticket.setStatus(status);
        ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketResponse.class);
    }

    public TicketResponse updateTicketPriority(Long ticketId, TicketPriority priority) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticket.setPriority(priority);
        ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketResponse.class);
    }
}
