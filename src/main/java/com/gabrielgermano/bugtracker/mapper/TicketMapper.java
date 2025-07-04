package com.gabrielgermano.bugtracker.mapper;

import com.gabrielgermano.bugtracker.model.Ticket;
import com.gabrielgermano.bugtracker.payload.request.TicketRequest;
import com.gabrielgermano.bugtracker.payload.response.TicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
    Ticket mapToTicket(TicketRequest ticketRequest);
    TicketResponse mapToTicketResponse(Ticket ticket);
    List<TicketResponse> mapToTicketResponseList(Collection<Ticket> tickets);
}
