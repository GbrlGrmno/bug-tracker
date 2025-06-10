package com.gabrielgermano.bugtracker.payload.request;

import jakarta.validation.constraints.NotNull;

public class ProjectMemberRequest {
    

    @NotNull(message = "Project ID cannot be null")
    private Long projectId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    public ProjectMemberRequest(Long projectId, Long userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
}
