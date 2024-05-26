package com.gabrielgermano.bugtrackerbackend.request;

import com.gabrielgermano.bugtrackerbackend.model.TicketPriority;
import com.gabrielgermano.bugtrackerbackend.model.TicketStatus;
import com.gabrielgermano.bugtrackerbackend.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketRequest {

    private String title;
    private String description;
    private TicketPriority priority;
    private TicketType type;
    private TicketStatus status;
}
