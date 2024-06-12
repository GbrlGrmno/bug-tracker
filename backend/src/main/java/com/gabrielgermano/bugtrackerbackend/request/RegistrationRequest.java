package com.gabrielgermano.bugtrackerbackend.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegistrationRequest {

    private String username;
    private String password;
    private String email;
}
