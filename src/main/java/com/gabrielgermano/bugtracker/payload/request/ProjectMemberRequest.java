package com.gabrielgermano.bugtracker.payload.request;

import jakarta.validation.constraints.NotNull;

public record ProjectMemberRequest(@NotNull(message = "Project ID cannot be null") Long projectId,
                                   @NotNull(message = "User ID cannot be null") Long userId) {


    public ProjectMemberRequest(Long projectId, Long userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

}
