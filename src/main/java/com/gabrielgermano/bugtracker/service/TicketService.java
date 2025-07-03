package com.gabrielgermano.bugtracker.service;

import com.gabrielgermano.bugtracker.exception.project.ProjectNotFoundException;
import com.gabrielgermano.bugtracker.exception.ticket.TicketNotFoundException;
import com.gabrielgermano.bugtracker.mapper.TicketMapper;
import com.gabrielgermano.bugtracker.model.Project;
import com.gabrielgermano.bugtracker.model.Ticket;
import com.gabrielgermano.bugtracker.payload.request.TicketRequest;
import com.gabrielgermano.bugtracker.payload.response.TicketResponse;
import com.gabrielgermano.bugtracker.repository.ProjectRepository;
import com.gabrielgermano.bugtracker.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ProjectRepository projectRepository;
    private final TicketMapper ticketMapper;

    public TicketService(TicketRepository ticketRepository, ProjectRepository projectRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.projectRepository = projectRepository;
        this.ticketMapper = ticketMapper;
    }


    public TicketResponse createTicket(Long projectId, TicketRequest ticketRequest) {

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Ticket ticket = ticketMapper.mapToTicket(ticketRequest);

        ticket.setProject(project);

        Ticket savedTicket = ticketRepository.save(ticket);

        return ticketMapper.mapToTicketResponse(savedTicket);
    }

    public List<TicketResponse> getAllTicketsFromProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Collection<Ticket> tickets = project.getTickets();
        return ticketMapper.mapToTicketResponseList(tickets);
    }

    public List<TicketResponse> getAllTickets() {
        return ticketMapper.mapToTicketResponseList(ticketRepository.findAll());
    }

    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
        return ticketMapper.mapToTicketResponse(ticket);
    }

    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }

        ticketRepository.deleteById(id);
    }

    public TicketResponse patchTicket(Long ticketId, TicketRequest ticketRequest) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException(ticketId));

        if (ticketRequest.title() != null) {
            ticket.setTitle(ticketRequest.title());
        }

        if (ticketRequest.description() != null) {
            ticket.setDescription(ticketRequest.description());
        }

        if (ticketRequest.priority() != null) {
            ticket.setPriority(ticketRequest.priority());
        }

        if (ticketRequest.type() != null) {
            ticket.setType(ticketRequest.type());
        }

        if (ticketRequest.status() != null) {
            ticket.setStatus(ticketRequest.status());
        }

        ticketRepository.save(ticket);

        return ticketMapper.mapToTicketResponse(ticket);
    }
}
