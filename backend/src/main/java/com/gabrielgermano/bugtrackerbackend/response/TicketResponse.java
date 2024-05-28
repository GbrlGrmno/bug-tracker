package com.gabrielgermano.bugtrackerbackend.response;

import com.gabrielgermano.bugtrackerbackend.model.TicketPriority;
import com.gabrielgermano.bugtrackerbackend.model.TicketStatus;
import com.gabrielgermano.bugtrackerbackend.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponse {

    private Long id;
    private String title;
    private String description;
    private Long createdBy;
    private TicketPriority priority;
    private TicketType type;
    private TicketStatus status;
}
