package com.gabrielgermano.bugtracker.service;

import com.gabrielgermano.bugtracker.exception.project.ProjectNotFoundException;
import com.gabrielgermano.bugtracker.model.Project;
import com.gabrielgermano.bugtracker.model.Ticket;
import com.gabrielgermano.bugtracker.payload.request.TicketRequest;
import com.gabrielgermano.bugtracker.payload.response.TicketResponse;
import com.gabrielgermano.bugtracker.repository.ProjectRepository;
import com.gabrielgermano.bugtracker.repository.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public TicketService(TicketRepository ticketRepository, ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.ticketRepository = ticketRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }


    public TicketResponse createTicket(Long projectId, TicketRequest ticketRequest) {

       Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
       Ticket ticket = modelMapper.map(ticketRequest, Ticket.class);

       ticket.setProject(project);

       Ticket savedTicket = ticketRepository.save(ticket);

       return modelMapper.map(savedTicket, TicketResponse.class);

    }
}
