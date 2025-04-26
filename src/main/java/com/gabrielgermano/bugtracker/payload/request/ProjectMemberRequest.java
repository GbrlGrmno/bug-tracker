package com.gabrielgermano.bugtracker.payload.request;

public class ProjectMemberRequest {
    

    private Long projectId;
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
