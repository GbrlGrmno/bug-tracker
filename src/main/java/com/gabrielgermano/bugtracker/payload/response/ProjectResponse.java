package com.gabrielgermano.bugtracker.payload.response;

import java.time.LocalDateTime;

public record ProjectResponse(Long id, String name, String description, String createdBy, LocalDateTime createdDate,
                              String lastModifiedBy, LocalDateTime lastModifiedDate) {

}
