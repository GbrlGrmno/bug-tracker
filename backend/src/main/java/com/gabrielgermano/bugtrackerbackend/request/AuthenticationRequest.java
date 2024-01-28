package com.gabrielgermano.bugtrackerbackend.request;

import com.gabrielgermano.bugtrackerbackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class AuthenticationRequest {

    private String username;
    private String password;
    private String email;

}
