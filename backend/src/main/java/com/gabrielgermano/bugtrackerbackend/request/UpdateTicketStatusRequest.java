package com.gabrielgermano.bugtrackerbackend.request;


import com.gabrielgermano.bugtrackerbackend.model.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTicketStatusRequest {

    private TicketStatus status;
}
