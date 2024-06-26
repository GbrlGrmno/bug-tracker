package com.gabrielgermano.bugtrackerbackend.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberResponse {

    private Long projectId;
    private Long userId;
    private String message;
}
