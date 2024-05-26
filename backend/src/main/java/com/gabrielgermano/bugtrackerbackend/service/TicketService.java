package com.gabrielgermano.bugtrackerbackend.service;

import com.gabrielgermano.bugtrackerbackend.model.Project;
import com.gabrielgermano.bugtrackerbackend.model.Ticket;
import com.gabrielgermano.bugtrackerbackend.repository.ProjectRepository;
import com.gabrielgermano.bugtrackerbackend.repository.TicketRepository;
import com.gabrielgermano.bugtrackerbackend.request.TicketRequest;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final ProjectRepository projectRepository;

    private final TicketRepository ticketRepository;

    private final ModelMapper modelMapper;
    public void createTicket(Long projectId, TicketRequest ticketRequest) {

        Project project = projectRepository.findById(projectId).orElseThrow();

        Ticket ticket = modelMapper.map(ticketRequest, Ticket.class);

        ticket.setProject(project);
        ticketRepository.save(ticket);

    }
}
