package com.gabrielgermano.bugtrackerbackend.request;

import com.gabrielgermano.bugtrackerbackend.model.TicketType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTicketTypeRequest {

    private TicketType type;
}
