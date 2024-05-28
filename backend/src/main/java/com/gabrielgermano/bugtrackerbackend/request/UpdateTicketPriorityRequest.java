package com.gabrielgermano.bugtrackerbackend.request;

import com.gabrielgermano.bugtrackerbackend.model.TicketPriority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTicketPriorityRequest {

    private TicketPriority priority;
}
