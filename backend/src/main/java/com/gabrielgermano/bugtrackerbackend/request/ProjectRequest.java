package com.gabrielgermano.bugtrackerbackend.request;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ProjectRequest {

    private String name;
    private String description;
}
